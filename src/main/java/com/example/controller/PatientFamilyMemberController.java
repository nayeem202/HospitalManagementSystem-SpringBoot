package com.example.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Patient;
import com.example.model.PatientFamilyMember;
import com.example.repository.PatientFamilyMemberService;
import com.example.repository.PatientService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class PatientFamilyMemberController {

	@Autowired
	private PatientFamilyMemberService familyMemberService;
	
	@Autowired
	private PatientService patientService;

	
	@GetMapping("/getSinglePatientMemberData/{id}")
	public ResponseEntity<Map> getPatientDataById(@PathVariable int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PatientFamilyMember model = familyMemberService.findByPatientId((long) id);
			map.put("model", model);
			map.put("message", "Data get successfully");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}

	}
	
	@PostMapping("/savePatientFamilyData")
	public ResponseEntity<Map> saveFormData(@ModelAttribute PatientFamilyMember patientFamilyMember,
		 @RequestParam("patientId") long patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Patient pmodel = patientService.findById(patientId).get();
			patientFamilyMember.setPatientModel(pmodel);		
			patientFamilyMember = familyMemberService.save(patientFamilyMember);
			map.put("status", "Success");
			map.put("data", patientFamilyMember);
			map.put("message", "Data saved successfully");
			return ResponseEntity.ok(map);

		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}

	}
	

	
}
