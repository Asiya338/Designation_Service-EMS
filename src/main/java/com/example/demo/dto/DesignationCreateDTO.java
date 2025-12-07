package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DesignationCreateDTO {

	@NotBlank(message = "Designation name is mandatory")
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank(message = "Designation code is mandatory")
	@Size(min = 2, max = 10)
	private String code;
}
