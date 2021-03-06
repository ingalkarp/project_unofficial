package com.sunbeam.service;

import java.util.List; 

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.dto.PersonDTO;
import com.sunbeam.model.Person;
import com.sunbeam.repository.PersonRepository;


@Service
@Transactional
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<PersonDTO> getAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO addPersonDetails(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO searchByEmailId(String emailId) {
		Person persistentPerson = personRepository.findByEmail(emailId);
		PersonDTO dto = new PersonDTO();
//		persistentDonor.setPassword("");
		BeanUtils.copyProperties(persistentPerson, dto);
		// TODO Auto-generated method stub
		return dto;
	}

	@Override
	public Boolean deletePersonDetails(String emailId) {
		Person persistentPerson = personRepository.findByEmail(emailId);
//		PersonDTO dto = new PersonDTO();
		persistentPerson.setDeleted(1);
		BeanUtils.copyProperties(persistentPerson, persistentPerson);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO getPersonDetails(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO updatePersonDetails(int personId, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validatePerson(String emailId, String password) {
		Person persistentPerson = personRepository.findByEmailAndPassword(emailId, password);
		if (persistentPerson != null) {
//			System.out.println(persistentPerson.getEmail());
//			System.out.println(searchByEmailId(emailId).getDeleted());
			if(searchByEmailId(emailId).getDeleted() == 0) {
				return persistentPerson.getRole();
			}
			else {
				return "0";
			}
			
		}
		else {
			return "0";
		}
//		System.out.println("persistent "+persistentPerson);
		
//		if(persistentPerson == null) {
//			return false;
//		}
//		else {
//			return true;
//		}
//		return persistentPerson;
	}

}
