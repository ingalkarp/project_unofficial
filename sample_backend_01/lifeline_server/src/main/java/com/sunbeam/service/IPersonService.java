package com.sunbeam.service;

import java.util.List; 

import com.sunbeam.dto.PersonDTO;
import com.sunbeam.model.Person;

public interface IPersonService {
	
	List<PersonDTO> getAllPersons();
	PersonDTO addPersonDetails(Person person);
	PersonDTO searchByEmailId(String emailId);
	Boolean deletePersonDetails(String emailId);
	PersonDTO getPersonDetails(int personId);
	PersonDTO updatePersonDetails(int personId,Person person);
	String validatePerson(String emailId, String password);
	
	
	
}
