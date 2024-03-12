package com.essentials.controller;

import java.util.List;

import com.essentials.data.vo.v1.PersonVOV1;
import com.essentials.data.vo.v2.PersonVOV2;
import com.essentials.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.essentials.services.PersonServices;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People",description = "Endpoints for Managing peoples")
public class PersonController {
    
	@Autowired
	private PersonServices service;
	
	@GetMapping (produces = {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML
	, com.essentials.util.MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all peoples", description = "Finds all peoples", tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
						@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = PersonVOV1.class))
						)
					}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
	})
	public List<PersonVOV1> findAllPerson() {
		List<PersonVOV1> personsV1=service.findAll();

		personsV1.stream().forEach(p -> {
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
	@Operation(summary = "Finds a Person", description = "Finds a Person", tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content
										(schema = @Schema(implementation = PersonVOV1.class))

								),
					@ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
					@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
			})
	public PersonVOV1 findByIdPerson(@PathVariable(value = "id") Long id) throws Exception{
		
		PersonVOV1 vov1= service.findById(id);

		// HATEOAS
		vov1.add(linkTo(methodOn(PersonController.class).findByIdPerson(id)).withSelfRel());
		return vov1;
	}
	
	@PostMapping(consumes =  {com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"},
				produces ={ com.essentials.util.MediaType.APPLICATION_JSON, com.essentials.util.MediaType.APPLICATION_XML,"application/x-yaml"})
	@Operation(summary = "Adds a new Person", description = "Adds a new Person by passing in a JSON,XML or YML", tags = {"People"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200",
							content = @Content
									(schema = @Schema(implementation = PersonVOV1.class))

					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
			})
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
	@Operation(summary = "Updates a Person", description = "Updates a Person by passing in a JSON,XML or YML", tags = {"People"},
			responses = {
					@ApiResponse(description = "Updated", responseCode = "200",
							content = @Content
									(schema = @Schema(implementation = PersonVOV1.class))

					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
					@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
			})
	public PersonVOV1 updatePerson(@RequestBody PersonVOV1 person) throws Exception {
		PersonVOV1 personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findByIdPerson(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Person", description = "Deleted a Person", tags = {"People"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
					@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
			})
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	
}
