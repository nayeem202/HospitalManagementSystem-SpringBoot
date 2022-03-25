package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.PatientFamilyMember;

@Repository
public interface PatientFamilyMemberService extends CrudRepository<PatientFamilyMember, Long> {
	@Query("Select add from PatientFamilyMember add where add.PatientModel.patientId = ?1") 
	PatientFamilyMember findByPatientId(long id);
	
	
}
