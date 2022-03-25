package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patientfamily")
public class PatientFamilyMember {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, length = 20)
	private long patientFamilyId;
	private String familyMemberName;
	private String nid;
	private String address;
	private String phone;
	private String relation;

	@OneToOne(
			cascade = { CascadeType.REMOVE})
	@JoinColumn(name = "patientId", nullable = true)
	
	private Patient PatientModel;

	public long getPatientFamilyId() {
		return patientFamilyId;
	}

	public void setPatientFamilyId(long patientFamilyId) {
		this.patientFamilyId = patientFamilyId;
	}

	public String getFamilyMemberName() {
		return familyMemberName;
	}

	public void setFamilyMemberName(String familyMemberName) {
		this.familyMemberName = familyMemberName;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Patient getPatientModel() {
		return PatientModel;
	}

	public void setPatientModel(Patient patientModel) {
		PatientModel = patientModel;
	}

}
