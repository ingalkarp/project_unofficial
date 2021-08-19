package com.sunbeam.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.sunbeam.model.Seeker;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long>{
	
	Seeker findByEmail(String emailId);
	Seeker findByStateName(String emailId);

}


