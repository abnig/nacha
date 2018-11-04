package com.nacha.domain.file;

import java.math.BigDecimal;
import java.util.Date;
/**
 
 Indicator, File ID (Modifier), File creation date, File creation time, Total trxn, Total creditamount, Total Debit amount ,Batch Count 
1,T, 181024,1045, 14, 1400,0,6 

 * @author abnig19
 *
 */
public class FileHeader {
	
	private final Integer indicator;
	private final String fileId;
	private final Date creationDate;
	private final Date creationTime;
	private final Integer totalTransactionCount;
	private final Integer totalBatchCount;
	private final BigDecimal totalCreditAmount;
	private final BigDecimal totalDebitAmount;
	
	public FileHeader(Integer indicator, String fileId, Date creationDate, Date creationTime,
			Integer totalTransactionCount, Integer totalBatchCount, BigDecimal totalCreditAmount,
			BigDecimal totalDebitAmount) {
		super();
		this.indicator = indicator;
		this.fileId = fileId;
		this.creationDate = creationDate;
		this.creationTime = creationTime;
		this.totalTransactionCount = totalTransactionCount;
		this.totalBatchCount = totalBatchCount;
		this.totalCreditAmount = totalCreditAmount;
		this.totalDebitAmount = totalDebitAmount;
	}
	
	public Integer getIndicator() {
		return indicator;
	}
	public String getFileId() {
		return fileId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public Integer getTotalTransactionCount() {
		return totalTransactionCount;
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
	
}
