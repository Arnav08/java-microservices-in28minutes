package com.in28min.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello world";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world");
	}

	@GetMapping("/hello-world-bean/{name}")
	public HelloWorldBean helloWorldName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world, %s", name));
	}

	@GetMapping("/hello-world-inter")
	public String helloWorldIntl() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
