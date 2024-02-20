package com.essentials.services;

import java.util.List;
import java.util.logging.*;

import com.essentials.data.vo.v1.PersonVOV1;
import com.essentials.data.vo.v2.PersonVOV2;
import com.essentials.mapper.DozerMapper;
import com.essentials.mapper.custom.versions.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.essentials.exceptions.ResourceNotFoundException;
import com.essentials.model.Person;
import com.essentials.repository.PersonRepository;

@Service
public class PersonServices {
	private Logger logger=Logger.getLogger(PersonServices.class.getName());
	
	
	@Autowired
	PersonRepository personRepository;

	@Autowired
	PersonMapper personMapper;

	public List<PersonVOV1> findAll() {
		logger.info("Finding all people!");

		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVOV1.class) ;
	}

	public PersonVOV1 findById(Long id) {
    	logger.info("Finding one person!");
    	
    	/*Person person=new Person();
    	person.setFirstName("João");
    	person.setLastName("Queiroz");
    	person.setAddress("Rio de Janeiro - RJ");
    	person.setGender("Male");*/
    	
    	var entity= personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

		// convertendo as modificações do model e inserindo em vo
		return DozerMapper.parseObject(entity, PersonVOV1.class);
    }


	public PersonVOV1 create(PersonVOV1 person) {
		logger.info("Creating one person!");

		var entity=DozerMapper.parseObject(person,Person.class);

		var vo=DozerMapper.parseObject(personRepository.save(entity), PersonVOV1.class);
		return vo;
	}


	// Create version V2 with new attribute
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person version v2 including new attribute!");

		var entity = personMapper.convertVoToEntity(person);

		var vo = personMapper.convertEntityToVo(personRepository.save(entity));
		return vo;
	}
	
	public PersonVOV1 update(PersonVOV1 person) {
		logger.info("Update one person!");
		
	Person entity=personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	
	
	entity.setFirstName(person.getFirstName());
	entity.setLastName(person.getLastName());
	entity.setAddress(person.getAddress());
	entity.setGender(person.getGender());
	
	
		var vo=DozerMapper.parseObject( personRepository.save(entity), PersonVOV1.class);
		return vo;
	}
  
	
	
	public void delete(Long id) {
    	logger.info("Delete one person!");
    	
    	var entity=personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    	personRepository.delete(entity);
    }
	
}
