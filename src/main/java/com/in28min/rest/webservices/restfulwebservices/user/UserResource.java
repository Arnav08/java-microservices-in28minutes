package com.in28min.rest.webservices.restfulwebservices.user;

import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
 	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.getUsers();
		
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id){
		 User user =service.findOne(id);
		 if(user ==null) {
			   throw new UserNotFoundException("id=>"+id);
		 }
		 
		 EntityModel<User> resource = EntityModel.of(user);
		 WebMvcLinkBuilder linkTo = 
					linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		 User user =service.deleteUser(id);
		 if(user ==null) {
			   throw new UserNotFoundException("id=>"+id);
		 }
	}

}


