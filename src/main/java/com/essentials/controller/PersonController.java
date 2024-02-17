package com.essentials.controller;

import java.util.List;

import com.essentials.data.vo.v1.PersonVOV1;
import com.essentials.data.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.essentials.services.PersonServices;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	@GetMapping (produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVOV1> findAllPerson() {
		return service.findAll();
	}
	
	
	@GetMapping(value = "/{id}",produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV1 findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		return service.findById(id);
	}
	
	@PostMapping(consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV1 createPerson(@RequestBody PersonVOV1 person){
		
		return service.create(person);
	}

    // New configuration informing version 2(v2)
    @PostMapping(value = "/v2",consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2Person(@RequestBody PersonVOV2 person){

        return service.createV2(person);
    }
	
	@PutMapping(consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV1 updatePerson(@RequestBody PersonVOV1 person){
		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	
}
