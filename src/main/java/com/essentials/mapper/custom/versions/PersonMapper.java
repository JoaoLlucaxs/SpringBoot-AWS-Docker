package com.essentials.mapper.custom.versions;

import com.essentials.data.vo.v2.PersonVOV2;
import com.essentials.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    // convert entity for VOV2
    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 personVOV2=new PersonVOV2();

        personVOV2.setId(person.getId());
        personVOV2.setFirstName(person.getFirstName());
        personVOV2.setLastName(person.getLastName());
        personVOV2.setGender(person.getGender());
        personVOV2.setBirthDay(new Date());
        personVOV2.setAddress(person.getAddress());

        return personVOV2;
    }

    // convert VOV2 for Entity
    public Person convertVoToEntity(PersonVOV2 personV2){
        Person entity=new Person();

        entity.setId(personV2.getId());
        entity.setFirstName(personV2.getFirstName());
        entity.setLastName(personV2.getLastName());
        entity.setGender(personV2.getGender());
        entity.setAddress(personV2.getAddress());
        //entity.setBirthDay(new Date());

        return entity;
    }
}
