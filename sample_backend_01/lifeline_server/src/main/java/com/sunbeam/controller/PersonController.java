package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.dto.PersonDTO;
import com.sunbeam.model.Person;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.repository.PersonRepository;
import com.sunbeam.service.IPersonService;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
	
	@Autowired
	private DonorRepository donorRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private IPersonService personService;
	
	

	@GetMapping("/person/{emailId}/{password}")
	public ResponseEntity<?> valiadateUser(@PathVariable String emailId, @PathVariable String password) {
//		System.out.println(emailId + password);
		String user = personService.validatePerson(emailId, password);	
		System.out.println("User" + user);
		if (user == "0") {
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.NOT_FOUND).body(false));
		}
		else if(user.equals("ADMIN")){
					System.out.println(emailId);	
						return ResponseEntity.ok(ResponseEntity.status(HttpStatus.OK).body(user));
					}
		else {
//			String role = 
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.OK).body(user));
		}
		//		System.out.println("in validate User "+ (personService.validatePerson(emailId, password)));
		
//				(personService.validatePerson(emailId, password),HttpStatus.NOT_FOUND);
//		return ResponseEntity.ok(new ResponseDTO<>(personService.validatePerson(emailId, password)));
	}
	
	@PostMapping("person/deletePerson/{emailId}")
	public ResponseEntity<?> deleteUser(@PathVariable String emailId){
		Boolean delete = personService.deletePersonDetails(emailId);
		return null;
	}
	
	
//	@PostMapping("/person/{role}")
//	public Donor createDonor(@RequestBody DonorDTO person, @PathVariable String role) {
//		System.out.println("Donor Data: " + person+"     "+role);
////		personRepository.save(donor.getId(),donor.getEmail(),donor.getPassword());
//		Person p = new Person(person.getEmail(), person.getPassword(), role);
//		if (role.equalsIgnoreCase("donor")) {
//			Donor d = new Donor(person.getDonorId(),person.getFirstName(), person.getLastName(),
//					person.getGender(), person.getPhoneNumber(), person.getDateOfBirth(),
//					person.getRegistrationDate(),person.getBloodGroup(),
//					person.getAge(), person.getTestDate(),
//					person.getLabConfirmedCovid(), person.getLastSymptomDate(),
//					person.getPastDays(), person.getFollowUp(),person.getVaccineReceived(),
//					person.getAvailability(), person.getAddress(),person.getEmail(),
//					person.getPassword(), person.getPerson());
//			d.setPerson(p);
//			return donorRepository.save(d);
//		}
//		else {
////			return personRepository.save(p);
//			return null;
//		}
		
//	}
	@PostMapping("/seeker/{role}")
	public Person createDonor(@RequestBody PersonDTO person, @PathVariable String role) {
		System.out.println("Donor Data: " + person+"     "+role);
//		personRepository.save(donor.getId(),donor.getEmail(),donor.getPassword());
		Person p = new Person(person.getEmail(), person.getPassword(), role);
		if (role.equalsIgnoreCase("seeker")) {
//			Donor d = new Donor(person.getFirstName(), person.getLastName(),
//					person.getGender(), person.getPhoneNumber(), person.getDateOfBirth(),
//					person.getRegistrationDate(),person.getBloodGroup(),
//					person.getAge(), person.getTestDate(),
//					person.getLabConfirmedCovid(), person.getLastSymptomDate(),
//					person.getPastDays(), person.getFollowUp(),person.getVaccineReceived(),
//					person.getAvailability(), person.getAddress(),person.getEmail(),
//					person.getPassword(), person.getPerson());
//			d.setPerson(p);
			return personRepository.save(p);
		}
		else {
//			return personRepository.save(p);
			return null;
		}
		
	}
}
