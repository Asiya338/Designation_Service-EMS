package com.example.demo.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ErrorCodeEnum {

	GENERIC_EXCEPTION("10001", "Unexpected error in Designation service.."),
	METHOD_ARGUMENT_INVALID("10002", "Invalid method arguments provided.."),
	HTTP_MESSAGE_NOT_READABLE_EXCEPTION("10003", "Malformed JSON request.."),
	DATA_INTEGRITY_VIOLATION_EXCEPTION("10004", "Data integrity violation error.."),
	ILLEGAL_ARGUMENT_EXCEPTION("10005", "Illegal argument provided.."),
	PROPERTY_REFERENCE_EXCEPTION("10006", "Property reference issue occurred.."),
	RESOURCE_WITH_ID_NOT_FOUND("10008", "No Department with department ID"),
	DUPLICATE_DESIGNATION_NAME("10009", "Duplicate designation name provided."),
	DUPLICATE_DESIGNATION_CODE("10010", "Duplicate designation code provided.");

	String errorCode;
	String errorMessage;

	ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
