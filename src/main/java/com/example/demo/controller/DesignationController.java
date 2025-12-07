package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DesignationCreateDTO;
import com.example.demo.dto.DesignationResponseDTO;
import com.example.demo.dto.DesignationUpdateDTO;
import com.example.demo.service.DesignationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/designations")
public class DesignationController {

	private final DesignationService designationService;

	@PostMapping()
	public ResponseEntity<DesignationResponseDTO> createDesignation(
			@Valid @RequestBody DesignationCreateDTO createDTO) {

		log.info("Creating designation with details : {} ", createDTO);

		DesignationResponseDTO response = designationService.createDesignation(createDTO);

		URI location = URI.create("/api/v1/designations/" + response.getId());

		log.info("Designation created : {} ", response);

		return ResponseEntity.created(location).body(response);
	}

	@GetMapping()
	public ResponseEntity<List<DesignationResponseDTO>> getAllDesignations() {

		log.info("Fetching all designations");

		List<DesignationResponseDTO> response = designationService.getAllDesignations();

		log.info("All designations : {} ", response);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{designationId}")
	public ResponseEntity<DesignationResponseDTO> getDesignationById(@PathVariable Long designationId) {

		log.info("Fetch designation by designationId : {} ", designationId);

		DesignationResponseDTO response = designationService.getDesignationById(designationId);

		log.info("Designation by ID: {} || Designation : {} ", designationId, response);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{designationId}")
	public ResponseEntity<DesignationResponseDTO> updateDesignationById(@PathVariable Long designationId,
			@Valid @RequestBody DesignationUpdateDTO updateDTO) {

		log.info("Update designation by designationId : {} || UPDATE details : {}  ", designationId, updateDTO);

		DesignationResponseDTO response = designationService.updateDesignationById(designationId, updateDTO);

		log.info("Designation updated with ID : {} || updated designation : {}", designationId, response);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{designationId}")
	public ResponseEntity<DesignationResponseDTO> deleteDesignationById(@PathVariable Long designationId) {

		log.info("Delete designation by designationId : {} ", designationId);

		DesignationResponseDTO response = designationService.deleteDesignationById(designationId);

		log.info("Designation deleted with ID : {} || deleted designation : {} ", designationId, response);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/check/{designationId}")
	public ResponseEntity<DesignationResponseDTO> validateDesignationById(@PathVariable Long designationId) {

		log.info("Validate designation exists by designationId : {} ", designationId);

		DesignationResponseDTO response = designationService.validateDesignationById(designationId);

		log.info("Designation validated with ID : {} || designation : {}", designationId, response);

		return ResponseEntity.ok(response);
	}
}
