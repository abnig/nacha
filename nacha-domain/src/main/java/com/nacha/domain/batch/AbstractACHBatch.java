package com.nacha.domain.batch;

import java.math.BigDecimal;
import java.util.Calendar;

import com.nacha.domain.enums.ACHFileBatchDescription;
import com.nacha.domain.enums.ACHServiceClassCode;
import com.nacha.domain.enums.ACHStandardEntryClassCode;

public abstract class AbstractACHBatch {

	private final String indicator;
	private final String accountNumber;
	private final ACHFileBatchDescription achFileBatchDescription;
	private final ACHServiceClassCode achServiceClassCode;
	private final ACHStandardEntryClassCode achStandardEntryClassCode;
	private final Long batchNumber;
	private final Calendar effectiveEntryDate;
	private final BigDecimal totalCreditAmount;
	private final BigDecimal totalDebitAmount;
	private final Integer numberOfTransactionsInBatch;

	public AbstractACHBatch(String indicator, ACHServiceClassCode achServiceClassCode, String accountNumber,
			ACHStandardEntryClassCode achStandardEntryClassCode, ACHFileBatchDescription achFileBatchDescription,
			Calendar effectiveEntryDate, BigDecimal totalCreditAmount, BigDecimal totalDebitAmount, Long batchNumber,
			Integer numberOfTransactionsInBatch) {

		this.indicator = indicator;
		this.accountNumber = accountNumber;
		this.achFileBatchDescription = achFileBatchDescription;
		this.achServiceClassCode = achServiceClassCode;
		this.achStandardEntryClassCode = achStandardEntryClassCode;
		this.batchNumber = batchNumber;
		this.effectiveEntryDate = effectiveEntryDate;
		this.totalCreditAmount = totalCreditAmount;
		this.totalDebitAmount = totalDebitAmount;
		this.numberOfTransactionsInBatch = numberOfTransactionsInBatch;
	}

	public AbstractACHBatch(AbstractACHBatch abstractACHBatch) {
		this.indicator = abstractACHBatch.getIndicator();
		this.accountNumber = abstractACHBatch.getAccountNumber();
		this.achFileBatchDescription = abstractACHBatch.getAchFileBatchDescription();
		this.achServiceClassCode = abstractACHBatch.getAchServiceClassCode();
		this.achStandardEntryClassCode = abstractACHBatch.getAchStandardEntryClassCode();
		this.batchNumber = abstractACHBatch.getBatchNumber();
		this.effectiveEntryDate = abstractACHBatch.getEffectiveEntryDate();
		this.totalCreditAmount = abstractACHBatch.getTotalCreditAmount();
		this.totalDebitAmount = abstractACHBatch.getTotalDebitAmount();
		this.numberOfTransactionsInBatch = abstractACHBatch.getNumberOfTransactionsInBatch();
	}

	public String getIndicator() {
		return indicator;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public ACHFileBatchDescription getAchFileBatchDescription() {
		return achFileBatchDescription;
	}

	public ACHServiceClassCode getAchServiceClassCode() {
		return achServiceClassCode;
	}

	public ACHStandardEntryClassCode getAchStandardEntryClassCode() {
		return achStandardEntryClassCode;
	}

	public Long getBatchNumber() {
		return batchNumber;
	}

	public Calendar getEffectiveEntryDate() {
		return effectiveEntryDate;
	}

	public BigDecimal getTotalCreditAmount() {
		return totalCreditAmount;
	}

	public BigDecimal getTotalDebitAmount() {
		return totalDebitAmount;
	}

	public Integer getNumberOfTransactionsInBatch() {
		return numberOfTransactionsInBatch;
	}

	public abstract void executeValidation();

}
