package com.nacha.domain.transaction;

import java.math.BigDecimal;

import com.nacha.domain.transaction.validator.TransactionValidator;

public class ReversalTransaction extends AbstractACHTransaction {

	public ReversalTransaction(AbstractACHTransaction abstractACHTransaction, BigDecimal debitAmount) {
		super(abstractACHTransaction);
		this.debitAmount = debitAmount;
	}

	private final BigDecimal debitAmount;
	private TransactionValidator<ReversalTransaction> transactionValidator;
	
	public BigDecimal getDebitAmount() {
		return this.debitAmount;
	}

	@Override
	public void executeValidation() {
		this.transactionValidator.validateTransaction(this);
	}

}
