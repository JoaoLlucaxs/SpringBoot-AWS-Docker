package com.essentials.controller;

import java.util.List;

import com.essentials.data.vo.v1.PersonVOV1;
import com.essentials.data.vo.v2.PersonVOV2;
import com.essentials.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.essentials.services.PersonServices;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	@GetMapping (produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML
	, com.essentials.util.MediaType.APPLICATION_YML})
	public List<PersonVOV1> findAllPerson() {
		List<PersonVOV1> personsV1=service.findAll();

		personsV1.stream()
				.forEach(p -> {
                            try {
                                p.add(linkTo(methodOn(PersonController.class)
                                        .findByIdPerson(p.getKey())).withSelfRel()
                                    );
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
				);

		return personsV1;
	}
	
	
	@GetMapping(value = "/{id}",produces = {com.essentials.util.MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
	,"application/x-yaml"})
	public PersonVOV1 findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		PersonVOV1 vov1= service.findById(id);

		// HATEOAS
		vov1.add(linkTo(methodOn(PersonController.class).findByIdPerson(id)).withSelfRel());
		return vov1;
	}
	
	@PostMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
				produces ={ com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
	public PersonVOV1 createPerson(@RequestBody PersonVOV1 person) throws Exception {
		PersonVOV1 personVO = service.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findByIdPerson(personVO.getKey())).withSelfRel());
		return personVO;
	}

    // New configuration informing version 2(v2) (OPTIONAL)
  //  @PostMapping(value = "/v2",consumes =  org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
    //        produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    //public PersonVOV2 createV2Person(@RequestBody PersonVOV2 person){

      //  return service.createV2(person);
    //}
	
	@PutMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
			produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
	public PersonVOV1 updatePerson(@RequestBody PersonVOV1 person) throws Exception {
		PersonVOV1 personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findByIdPerson(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	
}
