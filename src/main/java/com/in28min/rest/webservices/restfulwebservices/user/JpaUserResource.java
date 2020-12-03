package com.in28min.rest.webservices.restfulwebservices.user;

import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class JpaUserResource {
 
	@Autowired
	UserRepositiry userRepository;
	
	@Autowired
	PostRepositiry postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
		
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id){
		 Optional<User> user =userRepository.findById(id);
		 if(!user.isPresent()) {
			   throw new UserNotFoundException("id="+id);
		 }
		 
		 EntityModel<User> resource = EntityModel.of(user.get());
		 WebMvcLinkBuilder linkTo = 
					linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		  userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/user/{id}/posts")
	public List<Post> getUserPosts(@PathVariable int id){
		 Optional<User> user =userRepository.findById(id);
		 if(!user.isPresent()) {
			   throw new UserNotFoundException("id="+id);
		 }
		return user.get().getPost();
		     
	}
	
	@PostMapping("/jpa/user/{id}/posts")
	public ResponseEntity<Object> createUser(@PathVariable int id, @RequestBody Post post) {
		Optional<User> optionalUser =userRepository.findById(id);
		 if(!optionalUser.isPresent()) {
			   throw new UserNotFoundException("id="+id);
		 }
		User user =optionalUser.get();
		post.setUser(user); 
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}

}


