package com.sunbeam.controller;

import org.springframework. beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ResponseDTO;
import com.sunbeam.dto.SeekerDTO;
import com.sunbeam.model.Person;
import com.sunbeam.model.Seeker;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.repository.PersonRepository;
import com.sunbeam.repository.SeekerRepository;
import com.sunbeam.service.IDonorService;
import com.sunbeam.service.ISeekerService;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class SeekerController {
	
	static final Integer REQUST_MADE=1;

	@Autowired
	private IDonorService donorService;
	
	@Autowired
	private ISeekerService seekerService;
	
	

	@Autowired
	private DonorRepository donorRepository;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SeekerRepository seekerRepository;
	
	

//	@GetMapping("/donorsa")
//	public ResponseEntity<?> getAllSeekers() {
//		System.out.println("in get all donors");
//		return ResponseEntity.ok(new ResponseDTO<>(donorService.getAllS()));
//	}

//	@PostMapping("/donors")
//	public ResponseEntity<?> addDonorDetails(@RequestBody Donor donor) {
//		System.out.println("in add donor details " + donor);
//		return ResponseEntity.ok(new ResponseDTO<>(donorService.addDonorDetails(donor)));
//	}

	@GetMapping("/seeker/{emailId}")
	public ResponseEntity<?> searchByEmailId(@PathVariable String emailId) {
//		System.out.println(emailId);
//		System.out.println(ResponseEntity.ok(new ResponseDTO<>(donorService.searchByEmailId(emailId))));
		return ResponseEntity.ok(new ResponseDTO<>(seekerService.getSeekerDetails(emailId)));
	}

//	@GetMapping("/dono/{personId}")
//	public ResponseEntity<?> searchByPersonId(@PathVariable Integer personId) {
//		System.out.println(personId);
////		System.out.println(ResponseEntity.ok(new ResponseDTO<>(donorService.searchByPersonId(personId))));
////		return ResponseEntity.ok(new ResponseDTO<>(donorService.searchByEmailId(personId)));
//		return null;
//	}

	// create donor rest api

//	@PostMapping("/donors")
//	public Donor createDonor(@RequestBody Donor donor) {
////		System.out.println(donor.getEmail());
////		System.out.println("PErson id" + person.getPersonId());
////		System.out.println("Donor Data: " + donor);
////		personRepository.save(donor.getId(),donor.getEmail(),donor.getPassword());
//		return donorRepository.save(donor);
//	}

	@PostMapping("/seeker")
	public ResponseEntity<?> createSeeker(@RequestBody SeekerDTO seeker) {
		
		if (personRepository.existsByEmail(seeker.getEmail())) {
			Person persistentPerson = personRepository.findByEmail(seeker.getEmail());
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
				seekerService.updateSeekerDetails(seeker);
				return ResponseEntity.ok(ResponseEntity.status(HttpStatus.ACCEPTED).body(false));
			}
		}
		else {
			System.out.println(seeker.getEmail()+ "   " +seeker.getPassword());
			
			Person p = new Person(seeker.getEmail(), seeker.getPassword(),"SEEKER",0);
			System.out.println(p);
			Seeker s = new Seeker(seeker.getFirstName(), seeker.getLastName(),
					seeker.getPhoneNumber(),seeker.getBloodGroup(),
					seeker.getAge(), seeker.getLabConfirmedCovid(),
					seeker.getTestDate(),seeker.getStateName(),
					seeker.getSeekingRightNow(),seeker.getEmail(),seeker.getPerson());
			
			s.setPerson(p);
			System.out.println("Seeker" + s);
			seekerRepository.save(s);
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.ACCEPTED).body(false));
			
			
//			donorService.addDonorDetails(donor);
		}
	
			

//		if ()){

//		}
		
//	return null;

	}

	@PostMapping("/editSeeker")
	public ResponseEntity<?> updateSeekerDetails(@RequestBody SeekerDTO seeker) {
		
		Boolean update = seekerService.updateSeekerDetails(seeker);
		if (update == true) {
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.ACCEPTED).body(true));	
		}
		else {
			return ResponseEntity.ok(ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(false));
		}
	}
	
	@GetMapping("/donorsForSeekerByBloodGroup/{bloodGroup}")
	public ResponseEntity<?> getAllDonorsByBloodGroup(@PathVariable String bloodGroup){
		return ResponseEntity.ok(new ResponseDTO<>(seekerService.getAllDonorsByBloodGroup(bloodGroup)));
	}
	
	@GetMapping("/donorsForSeekerByStateName/{stateName}")
	public ResponseEntity<?> getAllDonorsByStateName(@PathVariable String stateName){
		return ResponseEntity.ok(new ResponseDTO<>(seekerService.getAllDonorsByStateName(stateName)));
	}
	
	@GetMapping("/donorsForSeekerByBloodGroupAndStateName/{bloodGroup}/{stateName}")
	public ResponseEntity<?> getAllDonorsByBloodGroupAndStateName(@PathVariable String bloodGroup, @PathVariable String stateName){
		return ResponseEntity.ok(new ResponseDTO<>(seekerService.getAllDonorsByBloodGroupAndStateName(bloodGroup, stateName)));
	}
	
//me	@GetMapping("/seekerGetAllDonors/{emailId}")
//	public ResponseEntity<?> seekerGetAllDonors(@PathVariable String emailId){
//		System.out.println("I was here");
//		return ResponseEntity.ok(new ResponseDTO<>(seekerService.seekerGetAllDonors(emailId)));
////		return ResponseEntity.ok(new ResponseDTO<>(seekerService.seekerGetAllDonorsWithRequestStatus(emailId)));
//	}
	
	
	
	
}
