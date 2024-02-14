package com.essentials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.essentials.model.Person;
import com.essentials.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	@GetMapping (produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAllPerson() {
		return service.findAll();
	}
	
	
	@GetMapping(value = "/{id}",produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Person findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		return service.findById(id);
	}
	
	@PostMapping(consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) throws Exception{
		
		return service.create(person);
	}
	
	@PutMapping(consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person) throws Exception{
		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	
}
