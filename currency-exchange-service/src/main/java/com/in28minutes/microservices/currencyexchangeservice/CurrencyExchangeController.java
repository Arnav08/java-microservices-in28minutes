package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment env;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue reterieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue evalu = currencyExchangeRepository.findByFromAndTo(from, to);
		evalu.setPort(Integer.parseInt(env.getProperty("server.port")));
		return evalu;

	}
}
