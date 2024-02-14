package com.essentials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.essentials.model.Person;
import com.essentials.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	
	@RequestMapping(method = RequestMethod.GET,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAllPerson() {
		return service.findAll();
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Person findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		return service.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) throws Exception{
		
		return service.create(person);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person) throws Exception{
		
		return service.update(person);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);
	}
	
	
}
