package com.sunbeam.service;

import java.util.List; 

import com.sunbeam.dto.DonorDTO;

import com.sunbeam.dto.SeekerDTO;

public interface ISeekerService {
	
	List<DonorDTO> getAllDonorsByBloodGroup(String bloodGroup);
	List<DonorDTO> getAllDonorsByStateName(String stateName);
	List<DonorDTO> getAllDonorsByBloodGroupAndStateName(String bloodGroup, String stateName);
	String deleteSeekerDetails(String emailId);
	SeekerDTO getSeekerDetails(String emailId);
	Boolean updateSeekerDetails(SeekerDTO donor);
	
	List<DonorDTO> seekerGetAllDonorsWithRequestStatus(String emailId);
	
//	void seekerSendRequestToDonor(String seekerEmail, String donorEmail);
}
