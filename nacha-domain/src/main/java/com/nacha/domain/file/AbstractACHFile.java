package com.nacha.domain.file;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

public abstract class AbstractACHFile {

	private final Collection<Integer> errorCodes;
	private final Calendar fileCreationDateTime;
	private final String fileIdModifier;
	private final String filename;
	private final Integer totalBatchCount;
	private final BigDecimal totalCreditAmount;
	private final BigDecimal totalDebitAmount;
	private final Integer totalTransactionCount;
	private final Boolean containsError;
	private final Integer reversalBatchCount;
	
	public AbstractACHFile(AbstractACHFile abstractACHFile) {
		super();
		this.errorCodes = abstractACHFile.getErrorCodes();
		this.fileCreationDateTime = abstractACHFile.getFileCreationDateTime();
		this.fileIdModifier = abstractACHFile.getFileIdModifier();
		this.filename = abstractACHFile.getFilename();
		this.totalBatchCount = abstractACHFile.getTotalBatchCount();
		this.totalCreditAmount = abstractACHFile.getTotalCreditAmount();
		this.totalDebitAmount = abstractACHFile.getTotalDebitAmount();
		this.totalTransactionCount = abstractACHFile.getTotalTransactionCount();
		this.containsError = abstractACHFile.getContainsError();
		this.reversalBatchCount = abstractACHFile.getReversalBatchCount();
	}

	public AbstractACHFile(Collection<Integer> errorCodes, Calendar fileCreationDateTime, String fileIdModifier,
			String filename, Integer totalBatchCount, BigDecimal totalCreditAmount, BigDecimal totalDebitAmount,
			Integer totalTransactionCount, Boolean containsError, Integer reversalBatchCount) {
		super();
		this.errorCodes = errorCodes;
		this.fileCreationDateTime = fileCreationDateTime;
		this.fileIdModifier = fileIdModifier;
		this.filename = filename;
		this.totalBatchCount = totalBatchCount;
		this.totalCreditAmount = totalCreditAmount;
		this.totalDebitAmount = totalDebitAmount;
		this.totalTransactionCount = totalTransactionCount;
		this.containsError = containsError;
		this.reversalBatchCount = reversalBatchCount;
	}

	public Collection<Integer> getErrorCodes() {
		return errorCodes;
	}

	public Calendar getFileCreationDateTime() {
		return fileCreationDateTime;
	}

	public String getFileIdModifier() {
		return fileIdModifier;
	}

	public String getFilename() {
		return filename;
	}

	public Integer getTotalBatchCount() {
		return totalBatchCount;
	}

	public BigDecimal getTotalCreditAmount() {
		return totalCreditAmount;
	}

	public BigDecimal getTotalDebitAmount() {
		return totalDebitAmount;
	}

	public Integer getTotalTransactionCount() {
		return totalTransactionCount;
	}

	public Boolean getContainsError() {
		return containsError;
	}

	public Integer getReversalBatchCount() {
		return reversalBatchCount;
	}

	public abstract void executeValidation();
}
