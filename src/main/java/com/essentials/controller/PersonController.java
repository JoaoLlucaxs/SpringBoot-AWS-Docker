package com.essentials.controller;

import java.util.List;

import com.essentials.data.vo.v1.PersonVOV1;
import com.essentials.data.vo.v2.PersonVOV2;
import com.essentials.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.essentials.services.PersonServices;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	@GetMapping (produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML
	, com.essentials.util.MediaType.APPLICATION_YML})
	public List<PersonVOV1> findAllPerson() {
		return service.findAll();
	}
	
	
	@GetMapping(value = "/{id}",produces = {com.essentials.util.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	,"application/x-yaml"})
	public PersonVOV1 findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		return service.findById(id);
	}
	
	@PostMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
				produces ={ com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
	public PersonVOV1 createPerson(@RequestBody PersonVOV1 person){
		
		return service.create(person);
	}

    // New configuration informing version 2(v2) (OPTIONAL)
  //  @PostMapping(value = "/v2",consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
    //        produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    //public PersonVOV2 createV2Person(@RequestBody PersonVOV2 person){

      //  return service.createV2(person);
    //}
	
	@PutMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
			produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
	public PersonVOV1 updatePerson(@RequestBody PersonVOV1 person){
		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	
}
