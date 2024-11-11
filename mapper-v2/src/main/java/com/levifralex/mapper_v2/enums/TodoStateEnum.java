package com.levifralex.mapper_v2.enums;

public enum TodoStateEnum {

	ACTIVE(1, "Active"), INACTIVE(0, "Inactive");
	
	private int code;
	private String description;
	
	
    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	TodoStateEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}
}
