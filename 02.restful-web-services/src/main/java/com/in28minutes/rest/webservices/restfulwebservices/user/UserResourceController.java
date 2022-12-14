package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
public class UserResourceController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserDaoService service;

	public UserResourceController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id:" + id);
		
		return user;
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUserJPA(@PathVariable int id) {
		User user = service.findOne(id);

		if(user==null)
			throw new UserNotFoundException("id:" + id);

		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linktToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linktToUsers.withRel("with-all"));

		return model;
	}

	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}


	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
		//	@RequestHeader(name="Accept-Language", required=false) Locale locale
		) {

		return messageSource.getMessage("good.morning.message", null, "Default 'Hi There'", // locale
				LocaleContextHolder.getLocale());
	}

}
