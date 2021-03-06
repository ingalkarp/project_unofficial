package com.sunbeam.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunbeam.model.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long>{
	
	Donor findByEmail(String emailId);
//	 findByPerson(Integer personId);
	String deleteByEmail(String emailId);
	List<Donor> findByBloodGroup(String bloodGroup);
	List<Donor> findByStateName(String stateName);
	List<Donor> findByBloodGroupAndStateName(String bloodGroup, String stateName);

//	@QueryHint(value = "select d.first_name,d.last_name, d.blood_group, d.age,s.request_status from donor_info d left join  seeker_requests s on d.email_id = s.donor_email and d.availability=1", nativeQuery = true)
//	List <DonorDTO> findAllWithRequestStatus(String emailId);
}


