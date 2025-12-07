package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DesignationCreateDTO;
import com.example.demo.dto.DesignationResponseDTO;
import com.example.demo.dto.DesignationUpdateDTO;
import com.example.demo.service.DesignationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DesignationServiceImpl implements DesignationService {
	@Override
	public DesignationResponseDTO createDesignation(@Valid DesignationCreateDTO createDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesignationResponseDTO> getAllDesignations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DesignationResponseDTO getDesignationById(Long designationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DesignationResponseDTO updateDesignationById(Long designationId, @Valid DesignationUpdateDTO updateDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DesignationResponseDTO deleteDesignationById(Long designationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DesignationResponseDTO validateDesignationById(Long designationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
