package com.nacha.domain.file;

import java.util.Set;

import com.nacha.domain.composite.BatchTransactionCompositeObject;

public class CSVFile extends AbstractACHFile {
	
	private FileHeader fileHader;
	private Set<BatchTransactionCompositeObject> batchTransactionCompositeObject; 
	
	public CSVFile(AbstractACHFile abstractACHFile) {
		super(abstractACHFile);
	}
	
	public FileHeader getFileHader() {
		return fileHader;
	}

	public Set<BatchTransactionCompositeObject> getBatchTransactionCompositeObject() {
		return batchTransactionCompositeObject;
	}

	public void setFileHader(FileHeader fileHader) {
		this.fileHader = fileHader;
	}

	public void setBatchTransactionCompositeObject(Set<BatchTransactionCompositeObject> batchTransactionCompositeObject) {
		this.batchTransactionCompositeObject = batchTransactionCompositeObject;
	}

	@Override
	public void executeValidation() {
		// TODO Auto-generated method stub
		
	}
}
