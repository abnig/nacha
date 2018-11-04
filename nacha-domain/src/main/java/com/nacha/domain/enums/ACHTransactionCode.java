package com.nacha.domain.enums;

public enum ACHTransactionCode {
	
	CREDIT_CHECKING(22),
	CREDIT_SAVING(32),
	DEBIT_CHECKING(27),
	DEBIT_SAVING(37);
	
	private Integer code;
	
	private ACHTransactionCode(Integer  code) {
		this.code = code;
	}

}
