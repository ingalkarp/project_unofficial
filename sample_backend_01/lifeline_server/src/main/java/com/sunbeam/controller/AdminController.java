package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ResponseDTO;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.service.IAdminService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	
	
	@GetMapping("/admin_donorslist")
	public ResponseEntity<?> getAllDonors() {
		System.out.println("in get all donors");
		return ResponseEntity.ok(new ResponseDTO<>(adminService.getAllDonors()));
	}
	
	// REST request handling method to delete user details
		@DeleteMapping("/admin_delete_donor/{emailId}")
		public ResponseEntity<?> deleteUserDetails(@PathVariable String emailId) {
			System.out.println("in del user dtls " + emailId);
			try {
				return ResponseEntity.ok(new ResponseDTO<>(adminService.deleteUserDetails(emailId)));
			} catch (RuntimeException e) {
				System.out.println("err in delete " + e);
				return new ResponseEntity<>(new ResponseDTO<>("User details deletion failed"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		
		@GetMapping("/admin_seekerslist")
		public ResponseEntity<?> getAllSeekers() {
			System.out.println("in get all Seekers");
			return ResponseEntity.ok(new ResponseDTO<>(adminService.getAllSeekers()));
		}
		
		// REST request handling method to delete user details
			@DeleteMapping("/admin_delete_seeker/{emailId}")
			public ResponseEntity<?> deleteSeekerDetail(@PathVariable String emailId) {
				System.out.println("in del user dtls " + emailId);
				try {
					return ResponseEntity.ok(new ResponseDTO<>(adminService.deleteSeekerDetail(emailId)));
				} catch (RuntimeException e) {
					System.out.println("err in delete " + e);
					return new ResponseEntity<>(new ResponseDTO<>("User details deletion failed"),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}	
		
}
