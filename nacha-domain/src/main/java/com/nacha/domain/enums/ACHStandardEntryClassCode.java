package com.nacha.domain.enums;

public enum ACHStandardEntryClassCode {
	
	PPD(1,  "PPD"),
	CCD(2, "CCD");
	
	private final Integer code;
	private final String name;
	
	private ACHStandardEntryClassCode(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

}
