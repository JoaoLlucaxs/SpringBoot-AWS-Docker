package com.essentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.essentials.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
