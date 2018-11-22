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
	
	public FileHeader(FileHeader fileHeader) {
		super();
		this.indicator = fileHeader.getIndicator();
		this.fileId = fileHeader.getFileId();
		this.creationDate = fileHeader.getCreationDate();
		this.creationTime = fileHeader.getCreationTime();
		this.totalTransactionCount = fileHeader.getTotalTransactionCount();
		this.totalBatchCount = fileHeader.getTotalBatchCount();
		this.totalCreditAmount = fileHeader.getTotalCreditAmount();
		this.totalDebitAmount = fileHeader.getTotalDebitAmount();
	}
	
	public FileHeader(Integer indicator, String fileId, Date creationDate, Date creationTime,
			Integer totalTransactionCount, BigDecimal totalCreditAmount,
			BigDecimal totalDebitAmount,
			Integer totalBatchCount) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((indicator == null) ? 0 : indicator.hashCode());
		result = prime * result + ((totalBatchCount == null) ? 0 : totalBatchCount.hashCode());
		result = prime * result + ((totalCreditAmount == null) ? 0 : totalCreditAmount.hashCode());
		result = prime * result + ((totalDebitAmount == null) ? 0 : totalDebitAmount.hashCode());
		result = prime * result + ((totalTransactionCount == null) ? 0 : totalTransactionCount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileHeader other = (FileHeader) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (indicator == null) {
			if (other.indicator != null)
				return false;
		} else if (!indicator.equals(other.indicator))
			return false;
		if (totalBatchCount == null) {
			if (other.totalBatchCount != null)
				return false;
		} else if (!totalBatchCount.equals(other.totalBatchCount))
			return false;
		if (totalCreditAmount == null) {
			if (other.totalCreditAmount != null)
				return false;
		} else if (!totalCreditAmount.equals(other.totalCreditAmount))
			return false;
		if (totalDebitAmount == null) {
			if (other.totalDebitAmount != null)
				return false;
		} else if (!totalDebitAmount.equals(other.totalDebitAmount))
			return false;
		if (totalTransactionCount == null) {
			if (other.totalTransactionCount != null)
				return false;
		} else if (!totalTransactionCount.equals(other.totalTransactionCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileHeader [indicator=" + indicator + ", fileId=" + fileId + ", creationDate=" + creationDate
				+ ", creationTime=" + creationTime + ", totalTransactionCount=" + totalTransactionCount
				+ ", totalBatchCount=" + totalBatchCount + ", totalCreditAmount=" + totalCreditAmount
				+ ", totalDebitAmount=" + totalDebitAmount + "]";
	}
	
}
