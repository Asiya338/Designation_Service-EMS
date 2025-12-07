package com.example.demo.dto;

import com.example.demo.enums.DesignationStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignationUpdateDTO {

	@Size(min = 2, max = 50)
	private String name;

	@Size(min = 2, max = 10)
	private String code;

	private DesignationStatusEnum status;
}
