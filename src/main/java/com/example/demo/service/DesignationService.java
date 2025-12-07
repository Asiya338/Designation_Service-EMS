package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DesignationCreateDTO;
import com.example.demo.dto.DesignationResponseDTO;
import com.example.demo.dto.DesignationUpdateDTO;

import jakarta.validation.Valid;

public interface DesignationService {

	DesignationResponseDTO createDesignation(@Valid DesignationCreateDTO createDTO);

	List<DesignationResponseDTO> getAllDesignations();

	DesignationResponseDTO getDesignationById(Long designationId);

	DesignationResponseDTO updateDesignationById(Long designationId, @Valid DesignationUpdateDTO updateDTO);

	DesignationResponseDTO deleteDesignationById(Long designationId);

	DesignationResponseDTO validateDesignationById(Long designationId);

}
