package com.nacha.domain.enums;

public enum ACHServiceClassCode {
	
	ACH_CREDIT(1, "220"),
	ACH_DEBIT   (2, "225"),
	ACH_MIXED(3, "200");
	
	private final Integer code;
	private final String name;
	
	private ACHServiceClassCode(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	

}
