package com.in28min.rest.webservices.restfulwebservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue reteriveSomeBean() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		SomeBean bean = new SomeBean("value1","value2","value3");
	    MappingJacksonValue map = new MappingJacksonValue(bean);
	    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter); 
	    map.setFilters(filters);
	    return map;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue reteriveSomeBeanList() {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		List<SomeBean> listb = Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value1","value2","value3"));
	    MappingJacksonValue map = new MappingJacksonValue(listb);
	    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter); 
	    map.setFilters(filters);
	    return map;
	}
	
	
}





