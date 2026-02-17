package com.example.demo.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ErrorCodeEnum {

	GENERIC_EXCEPTION("20001", "Unexpected error in Designation service.."),
	METHOD_ARGUMENT_INVALID("20002", "Invalid method arguments provided.."),
	HTTP_MESSAGE_NOT_READABLE_EXCEPTION("20003", "Malformed JSON request.."),
	DATA_INTEGRITY_VIOLATION_EXCEPTION("20004", "Data integrity violation error.."),
	ILLEGAL_ARGUMENT_EXCEPTION("20005", "Illegal argument provided.."),
	PROPERTY_REFERENCE_EXCEPTION("20006", "Property reference issue occurred.."),
	RESOURCE_WITH_ID_NOT_FOUND("20008", "No Department with department ID"),
	DUPLICATE_DESIGNATION_NAME("20009", "Duplicate designation name provided."),
	DUPLICATE_DESIGNATION_CODE("20010", "Duplicate designation code provided.");

	String errorCode;
	String errorMessage;

	ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
