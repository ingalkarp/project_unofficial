package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.dto.ResponseDTO;
import com.sunbeam.model.Donor;
import com.sunbeam.model.Person;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.repository.PersonRepository;
import com.sunbeam.service.IDonorService;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class DonorController {

	@Autowired
	private IDonorService donorService;

	@Autowired
	private DonorRepository donorRepository;

	@Autowired
	private PersonRepository personRepository;

	// get all donors

	@GetMapping("/donorsa")
	public ResponseEntity<?> getAllDonors() {
		System.out.println("in get all donors");
		return ResponseEntity.ok(new ResponseDTO<>(donorService.getAllDonors()));
	}

//	@PostMapping("/donors")
//	public ResponseEntity<?> addDonorDetails(@RequestBody Donor donor) {
//		System.out.println("in add donor details " + donor);
//		return ResponseEntity.ok(new ResponseDTO<>(donorService.addDonorDetails(donor)));
//	}

	@GetMapping("/donors/{emailId}")
	public ResponseEntity<?> searchByEmailId(@PathVariable String emailId) {
//		System.out.println(emailId);
//		System.out.println(ResponseEntity.ok(new ResponseDTO<>(donorService.searchByEmailId(emailId))));
		return ResponseEntity.ok(new ResponseDTO<>(donorService.searchByEmailId(emailId)));
	}

	@GetMapping("/dono/{personId}")
	public ResponseEntity<?> searchByPersonId(@PathVariable Integer personId) {
		System.out.println(personId);
//		System.out.println(ResponseEntity.ok(new ResponseDTO<>(donorService.searchByPersonId(personId))));
//		return ResponseEntity.ok(new ResponseDTO<>(donorService.searchByEmailId(personId)));
		return null;
	}

	// create donor rest api

//	@PostMapping("/donors")
//	public Donor createDonor(@RequestBody Donor donor) {
////		System.out.println(donor.getEmail());
////		System.out.println("PErson id" + person.getPersonId());
////		System.out.println("Donor Data: " + donor);
////		personRepository.save(donor.getId(),donor.getEmail(),donor.getPassword());
//		return donorRepository.save(donor);
//	}

	@PostMapping("/donor")
	public ResponseEntity<?> createDonor(@RequestBody DonorDTO donor) {
		
		if (personRepository.existsByEmail(donor.getEmail())) {
			Person persistentPerson = personRepository.findByEmail(donor.getEmail());
			if (persistentPerson.getDeleted()==0) {
				System.out.println("Donor Already exists");
				return ResponseEntity.ok(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false));
			}
			else {
//				System.out.println("Donor exists but deleted");
				
//				PersonDTO p = new PersonDTO(donor.getEmail(), donor.getPassword(),"DONOR",0);
//				DonorDTO d = new DonorDTO (donor.getFirstName(), donor.getLastName(),
//						donor.getPhoneNumber(),donor.getBloodGroup(),
//						donor.getAge(), donor.getLabConfirmedCovid(), donor.getLastSymptomDate(),
//						donor.getpast14Days(), donor.getfollowUpNegReport(),donor.getVaccineReceived(),
//						donor.getAvailability(), donor.getstateName(),donor.getEmail(),donor.getPerson());
				
//				d.setPerson(p);
//				System.out.println("Donor====" + d);
				donorService.updateDonorDetails(donor);
				return ResponseEntity.ok(ResponseEntity.status(HttpStatus.ACCEPTED).body(false));
			}
		}
		else {
			
			Person p = new Person(donor.getEmail(), donor.getPassword(),"DONOR",0);
			Donor d = new Donor (donor.getFirstName(), donor.getLastName(),
					donor.getPhoneNumber(),donor.getBloodGroup(),
					donor.getAge(), donor.getLabConfirmedCovid(), donor.getLastSymptomDate(),
					donor.getpast14Days(), donor.getfollowUpNegReport(),donor.getVaccineReceived(),
					donor.getAvailability(), donor.getstateName(),donor.getEmail(),donor.getPerson());
			
			d.setPerson(p);
			donorRepository.save(d);
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.ACCEPTED).body(false));
			
			
//			donorService.addDonorDetails(donor);
		}
	
			

//		if ()){

//		}
		
//	return null;

	}

	@PostMapping("/editDonor")
	public ResponseEntity<?> updateDonorDetails(@RequestBody DonorDTO donor) {
		Boolean update = donorService.updateDonorDetails(donor);
		if (update == true) {
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.ACCEPTED).body(true));	
		}
		else {
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(false));
		}
	}
	


}
