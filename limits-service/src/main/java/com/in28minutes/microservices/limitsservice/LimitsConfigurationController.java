package com.in28minutes.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration config;

	@GetMapping("/limits")
	public LimitsConfiguration reteriveLimitsFromConfigurations() {
		return new LimitsConfiguration(config.getMaximum(), config.getMinimum());
	}
}
