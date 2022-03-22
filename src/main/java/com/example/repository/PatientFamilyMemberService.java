package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.PatientFamilyMember;

@Repository
public interface PatientFamilyMemberService extends CrudRepository<PatientFamilyMember, Long> {

}
