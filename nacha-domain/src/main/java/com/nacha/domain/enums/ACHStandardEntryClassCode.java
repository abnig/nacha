package com.nacha.domain.enums;

public enum ACHStandardEntryClassCode {
	
	CCD(2, "CCD"),
	PPD(1,  "PPD");
	
	private final Integer code;
	private final String name;
	
	private ACHStandardEntryClassCode(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

}
