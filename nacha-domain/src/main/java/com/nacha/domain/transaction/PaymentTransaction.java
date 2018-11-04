package com.nacha.domain.transaction;

import java.math.BigDecimal;

import com.nacha.domain.transaction.validator.TransactionValidator;

public class PaymentTransaction extends AbstractACHTransaction {

	public PaymentTransaction(AbstractACHTransaction abstractACHTransaction, BigDecimal creditAmount) {
		super(abstractACHTransaction);
		this.creditAmount = creditAmount;
	}

	private final BigDecimal creditAmount;
	private TransactionValidator<PaymentTransaction> transactionValidator;
	
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	@Override
	public void executeValidation() {
		this.transactionValidator.validateTransaction(this);
	}
}
