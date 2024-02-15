package com.essentials.services;

import java.util.List;
import java.util.logging.*;

import com.essentials.data.vo.v1.PersonVO;
import com.essentials.mapper.DozerMapper;
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

	
	public PersonVO findById(Long id) {
    	logger.info("Finding one person!");
    	
    	Person person=new Person();
    	person.setFirstName("João");
    	person.setLastName("Queiroz");
    	person.setAddress("Rio de Janeiro - RJ");
    	person.setGender("Male");
    	
    	var entity= personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		// convertendo as modificações do model e inserindo em vo
		return DozerMapper.parseObject(entity,PersonVO.class);
    }
	
	
	public List<PersonVO> findAll() {
		 logger.info("Finding all people!");
		 
		
		return DozerMapper.parseListObjects(personRepository.findAll(),PersonVO.class) ;
	}
	
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");

		var entity=DozerMapper.parseObject(person,Person.class);

		var vo=DozerMapper.parseObject(personRepository.save(entity),PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Update one person!");
		
	Person entity=personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	
	
	entity.setFirstName(person.getFirstName());
	entity.setLastName(person.getLastName());
	entity.setAddress(person.getAddress());
	entity.setGender(person.getGender());
	
	
		var vo=DozerMapper.parseObject( personRepository.save(entity),PersonVO.class);
		return vo;
	}
  
	
	
	public void delete(Long id) {
    	logger.info("Delete one person!");
    	
    	var entity=personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    	personRepository.delete(entity);
    }
	
}
