package com.sunbeam.service;

import java.util.List; 

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.model.Donor;

public interface IDonorService {
	
	List<DonorDTO> getAllDonors();
	DonorDTO addDonorDetails(DonorDTO donor);
	DonorDTO searchByEmailId(String emailId);
	DonorDTO searchByPersonId(Integer personId);
	String deleteDonorDetaild(int donorId);
	DonorDTO getDonorDetails(int donorId);
	Boolean updateDonorDetails(DonorDTO donor);
	
	
	
	
}
