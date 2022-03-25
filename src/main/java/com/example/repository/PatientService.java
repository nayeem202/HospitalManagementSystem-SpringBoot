package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Patient;
import com.example.model.PatientFamilyMember;

@Repository
public interface PatientService extends CrudRepository<Patient, Long>{
	


}
