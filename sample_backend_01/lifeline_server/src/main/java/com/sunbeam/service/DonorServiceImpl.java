package com.sunbeam.service;

import java.util.ArrayList; 
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.dto.SeekerDTO;
import com.sunbeam.model.Donor;
import com.sunbeam.model.Person;
import com.sunbeam.model.Seeker;
//import com.sunbeam.model.SeekerRequests;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.repository.PersonRepository;
import com.sunbeam.repository.SeekerRepository;
//import com.sunbeam.repository.SeekerRequestsRepository;

@Service
@Transactional
public class DonorServiceImpl implements IDonorService {

	@Autowired
	private DonorRepository donorRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private SeekerRepository seekerRepository;

//me	 @Autowired
//	private SeekerRequestsRepository seekerRequestsRepository;

	@Override
	public List<DonorDTO> getAllDonors() {
		List<DonorDTO> list = new ArrayList<>();
		donorRepository.findAll().forEach(u -> {
			DonorDTO dto = new DonorDTO();
			BeanUtils.copyProperties(u, dto);// password property will be silently ignored , since it doesn't exist in
												// User DTO
			list.add(dto);

		});
		System.out.println(list);
		return list;
	}

	@Override
	public DonorDTO addDonorDetails(DonorDTO donor) {

//		DonorDTO persistentDonor = donorRepository2.save(donor);
//		PersonDTO persistentPerson = personRepository2.save(donor);

//		PersonDTO p = new PersonDTO(donor.getEmail(), donor.getPassword(),"DONOR",0);
//		DonorDTO d = new DonorDTO (donor.getFirstName(), donor.getLastName(),
//				donor.getPhoneNumber(),donor.getBloodGroup(),
//				donor.getAge(), donor.getLabConfirmedCovid(), donor.getLastSymptomDate(),
//				donor.getpast14Days(), donor.getfollowUpNegReport(),donor.getVaccineReceived(),
//				donor.getAvailability(), donor.getstateName(),donor.getEmail(),donor.getPerson());
//		
//		d.setPerson(p);
//		PersonDTO savePerson = new PersonDTO();
//		DonorDTO saveDonor = new DonorDTO();
//		donorRepository.save(d);

//		BeanUtils.copyProperties(persistentDonor, saveDonor);
//		BeanUtils.copyProperties(persistentPerson, savePerson);
//		donorRepository.save(d);
//		
//		Donor persistentDonor = donorRepository.save(donor);
//		DonorDTO dto = new DonorDTO();
//		BeanUtils.copyProperties(persistentDonor, dto);
//		return dto;
		return null;
	}

	@Override
	public String deleteDonorDetaild(int donorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DonorDTO getDonorDetails(int donorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateDonorDetails(DonorDTO donor) {
		if (personRepository.existsByEmailAndPassword(donor.getEmail(), donor.getPassword())) {
			Donor persistentDonor = donorRepository.findByEmail(donor.getEmail());
			Person persistentPerson = personRepository.findByEmail(donor.getEmail());
			if (persistentPerson.getDeleted() == 1) {
				persistentPerson.setDeleted(0);
			}
			BeanUtils.copyProperties(donor, persistentDonor);
			BeanUtils.copyProperties(donor, persistentPerson);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DonorDTO searchByEmailId(String emailId) {
		Donor persistentDonor = donorRepository.findByEmail(emailId);
//		System.out.println("this is donor"+persistentDonor);
		DonorDTO dto = new DonorDTO();
		persistentDonor.setPerson(null);
		BeanUtils.copyProperties(persistentDonor, dto);
//		System.out.println("in searchByEmail"+dto);
		return dto;
	}

	@Override
	public DonorDTO searchByPersonId(Integer personId) {
//		Donor persistentDonor = donorRepository.findByPersonId(personId);
		DonorDTO dto = new DonorDTO();
//		BeanUtils.copyProperties(persistentDonor, dto);
		System.out.println("in searchByEmail" + dto);
		return dto;
	}



}
