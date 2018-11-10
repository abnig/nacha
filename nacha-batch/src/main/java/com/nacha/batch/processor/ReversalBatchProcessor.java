package com.nacha.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.batch.ReversalBatch;

public class ReversalBatchProcessor implements ItemProcessor<AbstractACHBatch, ReversalBatch> {

	@Override
	public ReversalBatch process(AbstractACHBatch item) throws Exception {
		ReversalBatch paymentBatch = new ReversalBatch(item, null, null, null);
		
		return paymentBatch;
	}
	

}
