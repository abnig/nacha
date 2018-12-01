package com.nacha.domain.file;

import java.util.Set;

import com.nacha.domain.composite.BatchTransactionCompositeObject;

public class CSVFile extends AbstractACHFile {
	
	private Set<BatchTransactionCompositeObject> batchTransactionCompositeObject; 
	
	public CSVFile(AbstractACHFile abstractACHFile) {
		super(abstractACHFile);
	}
	
	public Set<BatchTransactionCompositeObject> getBatchTransactionCompositeObject() {
		return batchTransactionCompositeObject;
	}

	public void setBatchTransactionCompositeObject(Set<BatchTransactionCompositeObject> batchTransactionCompositeObject) {
		this.batchTransactionCompositeObject = batchTransactionCompositeObject;
	}

	@Override
	public void executeValidation() {
		// TODO Auto-generated method stub
		
	}
}
