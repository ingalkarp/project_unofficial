package com.sunbeam.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.sunbeam.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
