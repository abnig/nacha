package com.nacha.domain.transaction;

import java.math.BigDecimal;
import java.util.Set;

import com.nacha.domain.enums.ACHTransactionCode;

public abstract class AbstractACHTransaction {

	private final String indicator;
	private final String accountNumber;
	private final ACHTransactionCode achTransactionCode;
	private final String addenda;
	private final Set<Integer> errorCodeSet;
	private final String identificationNumber;
	private final String routingNumber;
	private final Long transactionId;
	private final BigDecimal transactionAmount;
	private final String payeeName;

	public AbstractACHTransaction(String indicator, ACHTransactionCode achTransactionCode, String routingNumber,
			String accountNumber, BigDecimal transactionAmount, String identificationNumber, String payeeName,
			Long transactionId, String addenda, Set<Integer> errorCodeSet) {
		this.indicator = indicator;
		this.achTransactionCode = achTransactionCode;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.transactionAmount = transactionAmount;
		this.identificationNumber = identificationNumber;
		this.payeeName = payeeName;
		this.transactionId = transactionId;
		this.addenda = addenda;
		this.errorCodeSet = errorCodeSet;
	}

	public AbstractACHTransaction(AbstractACHTransaction abstractACHTransaction) {
		this.indicator = abstractACHTransaction.getIndicator();
		this.achTransactionCode = abstractACHTransaction.getAchTransactionCode();
		this.routingNumber = abstractACHTransaction.getRoutingNumber();
		this.accountNumber = abstractACHTransaction.getAccountNumber();
		this.transactionAmount = abstractACHTransaction.getTransactionAmount();
		this.identificationNumber = abstractACHTransaction.getIdentificationNumber();
		this.payeeName = abstractACHTransaction.getPayeeName();
		this.transactionId = abstractACHTransaction.getTransactionId();
		this.addenda = abstractACHTransaction.getAddenda();
		this.errorCodeSet = abstractACHTransaction.getErrorCodeSet();
	}

	public String getIndicator() {
		return indicator;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public String getPayeeName() {
		return payeeName;
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

	public Long getTransactionId() {
		return transactionId;
	}

	public abstract void executeValidation();

}
