package com.nacha.domain.file;

import java.util.Set;

import com.nacha.domain.composite.BatchTransactionCompositeObject;

public class CSVFile extends AbstractACHFile {
	
	private FileHeader fileHeader;
	private Set<BatchTransactionCompositeObject> batchTransactionCompositeObject; 
	
	public CSVFile(AbstractACHFile abstractACHFile) {
		super(abstractACHFile);
	}
	
	public FileHeader getFileHeader() {
		return fileHeader;
	}

	public Set<BatchTransactionCompositeObject> getBatchTransactionCompositeObject() {
		return batchTransactionCompositeObject;
	}

	public void setFileHeader(FileHeader fileHeader) {
		this.fileHeader = fileHeader;
	}

	public void setBatchTransactionCompositeObject(Set<BatchTransactionCompositeObject> batchTransactionCompositeObject) {
		this.batchTransactionCompositeObject = batchTransactionCompositeObject;
	}

	@Override
	public void executeValidation() {
		// TODO Auto-generated method stub
		
	}
}
