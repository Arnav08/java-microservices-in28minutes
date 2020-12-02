package com.in28min.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver sessionlocaleresolver = new AcceptHeaderLocaleResolver();
		sessionlocaleresolver.setDefaultLocale(Locale.US);
		return  sessionlocaleresolver;
	}

	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		return rbms;
	}

}
