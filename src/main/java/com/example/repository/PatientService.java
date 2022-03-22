package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Patient;

@Repository
public interface PatientService extends CrudRepository<Patient, Long>{
	

}
