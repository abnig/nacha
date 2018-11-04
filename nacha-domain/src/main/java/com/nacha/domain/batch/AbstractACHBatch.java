package com.nacha.domain.batch;

import java.util.Calendar;

import com.nacha.domain.enums.ACHFileBatchDescription;
import com.nacha.domain.enums.ACHServiceClassCode;
import com.nacha.domain.enums.ACHStandardEntryClassCode;

public abstract class AbstractACHBatch {

	private final String accountNumber;
	private final ACHFileBatchDescription achFileBatchDescription;
	private final ACHServiceClassCode achServiceClassCode;
	private final ACHStandardEntryClassCode achStandardEntryClassCode;
	private final Long batchNumber;
	private final Calendar effectiveEntryDate;

	public AbstractACHBatch(String accountNumber, ACHFileBatchDescription achFileBatchDescription,
			ACHServiceClassCode achServiceClassCode, ACHStandardEntryClassCode achStandardEntryClassCode,
			Long batchNumber, Calendar effectiveEntryDate) {
		super();
		this.accountNumber = accountNumber;
		this.achFileBatchDescription = achFileBatchDescription;
		this.achServiceClassCode = achServiceClassCode;
		this.achStandardEntryClassCode = achStandardEntryClassCode;
		this.batchNumber = batchNumber;
		this.effectiveEntryDate = effectiveEntryDate;
	}

	public AbstractACHBatch(AbstractACHBatch abstractACHBatch) {
		this.accountNumber = abstractACHBatch.getAccountNumber();
		this.achFileBatchDescription = abstractACHBatch.getAchFileBatchDescription();
		this.achServiceClassCode = abstractACHBatch.getAchServiceClassCode();
		this.achStandardEntryClassCode = abstractACHBatch.getAchStandardEntryClassCode();
		this.batchNumber = abstractACHBatch.getBatchNumber();
		this.effectiveEntryDate = abstractACHBatch.getEffectiveEntryDate();
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

	public abstract void executeValidation();

}
