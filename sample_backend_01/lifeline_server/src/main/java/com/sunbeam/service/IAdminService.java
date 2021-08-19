package com.sunbeam.service;

import java.util.List; 

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.dto.SeekerDTO;

public interface IAdminService {
	List<DonorDTO> getAllDonors();
	String deleteUserDetails(String emailId);
	
	List<SeekerDTO> getAllSeekers();
	String deleteSeekerDetail(String emailId);
}
