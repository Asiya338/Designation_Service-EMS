package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum DesignationStatusEnum {

	ACTIVE("Active"), INACTIVE("Inactive");

	private final String displayName;

	DesignationStatusEnum(String displayName) {
		this.displayName = displayName;
	}
}
