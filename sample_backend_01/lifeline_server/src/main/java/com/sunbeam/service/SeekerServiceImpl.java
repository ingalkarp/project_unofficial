package com.sunbeam.service;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.dto.DonorDTO;
import com.sunbeam.dto.SeekerDTO;
import com.sunbeam.model.Person;
import com.sunbeam.model.Seeker;
import com.sunbeam.repository.DonorRepository;
import com.sunbeam.repository.PersonRepository;
import com.sunbeam.repository.SeekerRepository;



@Service
@Transactional
public class SeekerServiceImpl implements ISeekerService {

	@Autowired
	private SeekerRepository seekerRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private DonorRepository donorRepository;

// me	@Autowired
//	private SeekerRequestsRepository seekerRequestsRepository;

	
	@Override
	public List<DonorDTO> getAllDonorsByStateName(String stateName) {
		List<DonorDTO> list = new ArrayList<>();
		donorRepository.findByStateName(stateName).forEach(u -> {
			DonorDTO dto = new DonorDTO();
			BeanUtils.copyProperties(u, dto);
			list.add(dto);
		});

		return list;
	}

	@Override
	public List<DonorDTO> getAllDonorsByBloodGroup(String bloodGroup) {
		List<DonorDTO> list = new ArrayList<>();
		donorRepository.findByBloodGroup(bloodGroup).forEach(u -> {
			DonorDTO dto = new DonorDTO();
			BeanUtils.copyProperties(u, dto);
			list.add(dto);
		});

		return list;
	}

	@Override
	public List<DonorDTO> getAllDonorsByBloodGroupAndStateName(String bloodGroup, String stateName) {
		List<DonorDTO> list = new ArrayList<>();
		donorRepository.findByBloodGroupAndStateName(bloodGroup, stateName).forEach(u -> {
			DonorDTO dto = new DonorDTO();
			BeanUtils.copyProperties(u, dto);
			list.add(dto);
		});

		return list;
	}

	@Override
	public String deleteSeekerDetails(String emailId) {
		Person persistentPerson = personRepository.findByEmail(emailId);
		persistentPerson.setDeleted(1);
		BeanUtils.copyProperties(persistentPerson, persistentPerson);
		return null;
	}

	@Override
	public SeekerDTO getSeekerDetails(String emailId) {
		Seeker persistentSeeker = seekerRepository.findByEmail(emailId);
		SeekerDTO seekerDTO = new SeekerDTO();
//		System.out.println("Hello"+persistentSeeker);
		persistentSeeker.setPerson(null);
		BeanUtils.copyProperties(persistentSeeker, seekerDTO);
		return seekerDTO;
	}

	@Override
	public Boolean updateSeekerDetails(SeekerDTO seeker) {
		if (personRepository.existsByEmailAndPassword(seeker.getEmail(), seeker.getPassword())) {
			Seeker persistentSeeker = seekerRepository.findByEmail(seeker.getEmail());
			Person persistentPerson = personRepository.findByEmail(seeker.getEmail());

			if (persistentPerson.getDeleted() == 1) {
				persistentPerson.setDeleted(0);
			}

			BeanUtils.copyProperties(seeker, persistentSeeker);
			BeanUtils.copyProperties(seeker, persistentPerson);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<DonorDTO> seekerGetAllDonorsWithRequestStatus(String EmailId) {
		// TODO Auto-generated method stub
		return null;
	}



}
