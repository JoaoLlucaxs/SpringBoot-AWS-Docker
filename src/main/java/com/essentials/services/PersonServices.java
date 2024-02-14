package com.essentials.services;

import java.util.List;
import java.util.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.essentials.exceptions.ResourceNotFoundException;
import com.essentials.model.Person;
import com.essentials.repository.PersonRepository;

@Service
public class PersonServices {
	private Logger logger=Logger.getLogger(PersonServices.class.getName());
	
	
	@Autowired
	private PersonRepository personRepository;

	
	public Person findById(Long id) {
    	logger.info("Finding one person!");
    	
    	Person person=new Person();
    	person.setFirstName("João");
    	person.setLastName("Queiroz");
    	person.setAddress("Rio de Janeiro - RJ");
    	person.setGender("Male");
    	
    	return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }
	
	
	public List<Person> findAll() {
		 logger.info("Finding all people!");
		 
		
		return personRepository.findAll();
	}
	
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Update one person!");
		
	Person entity=personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	
	
	entity.setFirstName(person.getFirstName());
	entity.setLastName(person.getLastName());
	entity.setAddress(person.getAddress());
	entity.setGender(person.getGender());
	
	
		return personRepository.save(entity);
	}
  
	
	
	public void delete(Long id) {
    	logger.info("Delete one person!");
    	
    	Person entity=personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    	personRepository.delete(entity);
    }
	
}
