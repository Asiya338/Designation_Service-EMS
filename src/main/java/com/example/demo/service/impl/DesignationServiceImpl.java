package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DesignationCreateDTO;
import com.example.demo.dto.DesignationResponseDTO;
import com.example.demo.dto.DesignationUpdateDTO;
import com.example.demo.entity.Designation;
import com.example.demo.enums.ErrorCodeEnum;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repo.DesignationRepo;
import com.example.demo.service.DesignationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DesignationServiceImpl implements DesignationService {

	private final DesignationRepo designationRepo;
	private final ModelMapper modelMapper;

	@Override
	public DesignationResponseDTO createDesignation(DesignationCreateDTO createDTO) {
		log.info("Create designation DTO : {}", createDTO);

		// Check duplicate title
		if (designationRepo.existsByName(createDTO.getName())) {
			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DESIGNATION_NAME.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DESIGNATION_NAME.getErrorMessage() + " : " + createDTO.getName());
		}

		// Check duplicate code
		if (designationRepo.existsByCode(createDTO.getCode())) {
			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DESIGNATION_CODE.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DESIGNATION_CODE.getErrorMessage() + " : " + createDTO.getCode());
		}

		Designation designation = new Designation();
		designation.setName(createDTO.getName());
		designation.setCode(createDTO.getCode().toUpperCase());

		Designation savedDesignation = designationRepo.save(designation);

		DesignationResponseDTO response = modelMapper.map(savedDesignation, DesignationResponseDTO.class);

		log.info("Designation created : {}", response);

		return response;
	}

	@Override
	public List<DesignationResponseDTO> getAllDesignations() {
		log.info("Fetching all designations");

		List<Designation> designations = designationRepo.findAll();

		List<DesignationResponseDTO> response = designations.stream()
				.map(designation -> modelMapper.map(designation, DesignationResponseDTO.class)).toList();

		log.info("All designations : {}", response);

		return response;
	}

	@Override
	public DesignationResponseDTO getDesignationById(Long designationId) {
		log.info("Fetching designation by ID : {}", designationId);

		Designation designation = designationRepo.findById(designationId).orElseThrow(
				() -> new ResourceNotFoundException(ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorCode(),
						ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorMessage() + " : " + designationId));

		DesignationResponseDTO response = modelMapper.map(designation, DesignationResponseDTO.class);

		log.info("Designation by ID : {} || {}", designationId, response);

		return response;
	}

	@Override
	public DesignationResponseDTO deleteDesignationById(Long designationId) {
		log.info("Deleting designation with ID : {}", designationId);

		Designation designation = designationRepo.findById(designationId).orElseThrow(
				() -> new ResourceNotFoundException(ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorCode(),
						ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorMessage() + " : " + designationId));

		designationRepo.delete(designation);

		DesignationResponseDTO response = new DesignationResponseDTO();
		response.setId(designationId);
		response.setDeletedAt(LocalDateTime.now());

		log.info("Designation deleted : {}", response);

		return response;
	}

	@Override
	public DesignationResponseDTO validateDesignationById(Long designationId) {
		log.info("Validating designation with ID : {}", designationId);

		DesignationResponseDTO response = new DesignationResponseDTO();

		if (!designationRepo.existsById(designationId)) {
			log.info("Designation not found: {}", designationId);
			response.setValid(false);
			return response;
		}

		Designation designation = designationRepo.findById(designationId).orElse(null);

		response = modelMapper.map(designation, DesignationResponseDTO.class);
		response.setValid(true);

		log.info("Designation validated : {}", response);

		return response;
	}

	@CacheEvict(value = "designationCache", key = "#dsgnId")
	@Override
	public DesignationResponseDTO updateDesignationById(Long id, DesignationUpdateDTO updateDTO) {
		log.info("Update designation DTO : {}", updateDTO);

		Designation designation = designationRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorCode(),
						ErrorCodeEnum.RESOURCE_WITH_ID_NOT_FOUND.getErrorMessage() + " : " + id + " || Update failed"));

		// Check duplicate title
		if (designationRepo.existsByName(updateDTO.getName())
				&& !updateDTO.getName().equalsIgnoreCase(designation.getName())) {

			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DESIGNATION_NAME.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DESIGNATION_NAME.getErrorMessage() + " : " + updateDTO.getName());
		}

		// Check duplicate code
		if (designationRepo.existsByCode(updateDTO.getCode())
				&& !updateDTO.getCode().equalsIgnoreCase(designation.getCode())) {

			throw new DuplicateResourceException(ErrorCodeEnum.DUPLICATE_DESIGNATION_CODE.getErrorCode(),
					ErrorCodeEnum.DUPLICATE_DESIGNATION_CODE.getErrorMessage() + " : " + updateDTO.getCode());
		}

		modelMapper.map(updateDTO, designation);

		Designation updatedDesignation = designationRepo.save(designation);

		DesignationResponseDTO response = modelMapper.map(updatedDesignation, DesignationResponseDTO.class);

		log.info("Designation updated : {}", response);

		return response;
	}
}
