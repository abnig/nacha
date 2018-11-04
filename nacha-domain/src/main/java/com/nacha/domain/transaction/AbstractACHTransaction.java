package com.nacha.domain.transaction;

import java.util.Set;

import com.nacha.domain.enums.ACHTransactionCode;

public abstract class AbstractACHTransaction {

	private final String accountNumber;
	private final ACHTransactionCode achTransactionCode;
	private final String addenda;
	private final Set<Integer> errorCodeSet;
	private final String identificationNumber;
	private final String routingNumber;
	private final Long traceNumber;
	private final Long transactionId;

	public AbstractACHTransaction(String accountNumber, ACHTransactionCode achTransactionCode, String addenda,
			Set<Integer> errorCodeSet, String identificationNumber, String routingNumber, Long traceNumber,
			Long transactionId) {
		super();
		this.accountNumber = accountNumber;
		this.achTransactionCode = achTransactionCode;
		this.addenda = addenda;
		this.errorCodeSet = errorCodeSet;
		this.identificationNumber = identificationNumber;
		this.routingNumber = routingNumber;
		this.traceNumber = traceNumber;
		this.transactionId = transactionId;
	}

	public AbstractACHTransaction(AbstractACHTransaction abstractACHTransaction) {
		this.accountNumber = abstractACHTransaction.getAccountNumber();
		this.achTransactionCode = abstractACHTransaction.getAchTransactionCode();
		this.addenda = abstractACHTransaction.getAddenda();
		this.errorCodeSet = abstractACHTransaction.getErrorCodeSet();
		this.identificationNumber = abstractACHTransaction.getIdentificationNumber();
		this.routingNumber = abstractACHTransaction.getRoutingNumber();
		this.traceNumber = abstractACHTransaction.getTraceNumber();
		this.transactionId = abstractACHTransaction.getTransactionId();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public ACHTransactionCode getAchTransactionCode() {
		return achTransactionCode;
	}

	public String getAddenda() {
		return addenda;
	}

	public Set<Integer> getErrorCodeSet() {
		return errorCodeSet;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public Long getTraceNumber() {
		return traceNumber;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public abstract void executeValidation();

}
