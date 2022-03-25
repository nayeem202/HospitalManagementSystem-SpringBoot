package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.model.Patient;
import com.example.repository.PatientFamilyMemberService;
import com.example.repository.PatientService;
import com.example.storage.service.FileStorageService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PatientFamilyMemberService familyMemberService;
	
	@Autowired
	private FileStorageService fileStorageService;

	// Save patient Data
	@PostMapping("/savePatientInfo")
	public ResponseEntity<Map> saveFormData(@ModelAttribute Patient patient, @RequestParam("file") MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileName).toUriString();

			patient.setProImage(fileName);
			patient.setImageUri(fileDownloadUri);

			patient = patientService.save(patient);
			map.put("status", "Success");
			map.put("data", patient);
			map.put("message", "Data saved successfully");
			return ResponseEntity.ok(map);

		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}

	}

	// Get All patient Data
	@GetMapping("/getAllPatientData")
	public ResponseEntity<Map> getAllPatientData() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Patient> model = (List<Patient>) (patientService).findAll();
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

	// Get Single patient Data
	@GetMapping("/getSinglePatientData/{id}")
	public ResponseEntity<Map> getPatientDataById(@PathVariable int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Optional<Patient> model = patientService.findById((long) id);
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
	
	// Update patient Data
	@PostMapping("/updatePatientData/{id}")
	public ResponseEntity<Map> saveFormData1(@ModelAttribute Patient patient,
			@RequestParam("file") MultipartFile file, @PathVariable int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(fileName).toUriString();
			patient.setProImage(fileName);
			patient.setImageUri(fileDownloadUri);
			patient = patientService.save(patient);
			map.put("status", "Success");
			map.put("data", patient);
			map.put("message", "Data saved successfully");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}

	}
	
	
	// delete patient Data
	@GetMapping("/deleteSinglePatientData/{patientId}")
	public ResponseEntity<Map> deletePatientDataById(@PathVariable int patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//patientService.deleteById((long) patientId);
			familyMemberService.deleteById((long) patientId);
			map.put("message", "Data  successfully deleted ");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("status", "failed");
			map.put("data", null);
			map.put("message", e.getLocalizedMessage());
			return ResponseEntity.status(500).body(map);
		}

	}
	
	
	

}
