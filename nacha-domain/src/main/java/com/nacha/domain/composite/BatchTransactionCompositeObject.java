package com.nacha.domain.composite;

import java.util.Collections;
import java.util.Set;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.transaction.AbstractACHTransaction;

public class BatchTransactionCompositeObject {

	private final AbstractACHBatch achBatch;

	private final Set<AbstractACHTransaction> transactionSet;

	public BatchTransactionCompositeObject(AbstractACHBatch achBatch, Set<AbstractACHTransaction> transactionSet) {
		super();
		this.achBatch = achBatch;
		this.transactionSet = transactionSet;
	}

	public AbstractACHBatch getAchBatch() {
		return achBatch;
	}

	public Set<AbstractACHTransaction> getTransactionSet() {
		return Collections.unmodifiableSet(this.transactionSet);
	}
	
	public <T extends AbstractACHTransaction> void addTransactionToSet(T t) {
		this.transactionSet.add(t);
	}
	
}
