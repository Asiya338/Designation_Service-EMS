package com.example.demo.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ErrorCodeEnum {

	GENERIC_EXCEPTION("10001", "Unexpected error in Employee Management System service.."),
	METHOD_ARGUMENT_INVALID("10002", "Invalid method arguments provided.."),
	HTTP_MESSAGE_NOT_READABLE_EXCEPTION("10003", "Malformed JSON request.."),
	DATA_INTEGRITY_VIOLATION_EXCEPTION("10004", "Data integrity violation error.."),
	ILLEGAL_ARGUMENT_EXCEPTION("10005", "Illegal argument provided.."), PROPERTY_REFERENCE_EXCEPTION("10006", ""),
	DUPLICATE_DEPT_CODE("10006", "Duplicate department code provided."),
	DUPLICATE_DEPT_HEAD_ID("10007", "Duplicate department head id provided."),
	RESOURCE_WITH_ID_NOT_FOUND("10008", "No Department with department ID")

	;

	String errorCode;
	String errorMessage;

	ErrorCodeEnum(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
