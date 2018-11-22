package com.nacha.domain.file;

import java.util.Calendar;
import java.util.Collection;

public abstract class AbstractACHFile {

	private final Collection<Integer> errorCodes;
	private final Calendar fileCreationDateTime;
	private final String fileIdModifier;
	private final String filename;
	private final Boolean containsError;
	private final Integer reversalBatchCount;
	private final FileHeader fileHeader;

	public AbstractACHFile(AbstractACHFile abstractACHFile) {
		super();
		this.errorCodes = abstractACHFile.getErrorCodes();
		this.fileCreationDateTime = abstractACHFile.getFileCreationDateTime();
		this.fileIdModifier = abstractACHFile.getFileIdModifier();
		this.filename = abstractACHFile.getFilename();
		this.containsError = abstractACHFile.getContainsError();
		this.reversalBatchCount = abstractACHFile.getReversalBatchCount();
		this.fileHeader = abstractACHFile.getFileHeader();
	}
	

	public AbstractACHFile(Collection<Integer> errorCodes, Calendar fileCreationDateTime, String fileIdModifier,
			String filename, Boolean containsError, Integer reversalBatchCount, FileHeader fileHeader) {
		super();
		this.errorCodes = errorCodes;
		this.fileCreationDateTime = fileCreationDateTime;
		this.fileIdModifier = fileIdModifier;
		this.filename = filename;
		this.containsError = containsError;
		this.reversalBatchCount = reversalBatchCount;
		this.fileHeader = fileHeader;
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

	public Boolean getContainsError() {
		return containsError;
	}

	public Integer getReversalBatchCount() {
		return reversalBatchCount;
	}

	public FileHeader getFileHeader() {
		return fileHeader;
	}

	public abstract void executeValidation();
}
