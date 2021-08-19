package com.sunbeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	Person findByEmailAndPassword(String emailId, String password);
	Boolean existsByEmailAndPassword(String emailId, String password);
	
	Person findByEmail(String emailId);
	
	Boolean existsByEmail(String emailId);
	
	Integer deleteByEmail(String emailId);

}
