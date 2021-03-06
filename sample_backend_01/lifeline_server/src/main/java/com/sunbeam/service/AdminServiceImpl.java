package com.sunbeam.service;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.dto.SeekerDTO;
import com.sunbeam.exceptions.ResourceNotFoundException;
import com.sunbeam.model.Donor;
import com.sunbeam.model.Person;
import com.sunbeam.model.Seeker;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.repository.PersonRepository;
import com.sunbeam.repository.SeekerRepository;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private DonorRepository donorRepository;
	
	@Autowired
	private SeekerRepository seekerRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public List<DonorDTO> getAllDonors() {
		List<DonorDTO> list = new ArrayList<>();
		donorRepository.findAll().forEach(u -> {
			DonorDTO dto = new DonorDTO();
			BeanUtils.copyProperties(u, dto);// password property will be silently ignored , since it doesn't exist in
												// User DTO
			list.add(dto);
			
		});
		//System.out.println(list);
		return list;
	}
	
	@Override
	public List<SeekerDTO> getAllSeekers() {
		List<SeekerDTO> list = new ArrayList<>();
		seekerRepository.findAll().forEach(u -> {
			SeekerDTO dto = new SeekerDTO();
			BeanUtils.copyProperties(u, dto);
			
			list.add(dto);
		});
		return list;
	}




	@Override
	public String deleteUserDetails(String emailId) {
		Donor donor = donorRepository.findByEmail(emailId);
		Person person = personRepository.findByEmail(emailId);
		donorRepository.deleteByEmail(emailId);
		personRepository.deleteByEmail(emailId);
		return "Donor Deleted "+emailId;
	}

	@Override
	public String deleteSeekerDetail(String emailId) {
		Seeker seeker = seekerRepository.findByEmail(emailId);
		Person person = personRepository.findByEmail(emailId);
		donorRepository.deleteByEmail(emailId);
		personRepository.deleteByEmail(emailId);
		return "Seeker Deleted "+emailId;
	}
	
	

}
