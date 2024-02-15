package com.essentials.controller;

import java.util.List;

import com.essentials.data.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.essentials.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	@GetMapping (produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAllPerson() {
		return service.findAll();
	}
	
	
	@GetMapping(value = "/{id}",produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		return service.findById(id);
	}
	
	@PostMapping(consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public PersonVO createPerson(@RequestBody PersonVO person) throws Exception{
		
		return service.create(person);
	}
	
	@PutMapping(consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public PersonVO updatePerson(@RequestBody PersonVO person) throws Exception{
		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	
}
