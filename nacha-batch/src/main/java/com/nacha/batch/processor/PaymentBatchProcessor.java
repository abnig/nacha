package com.nacha.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nacha.domain.batch.AbstractACHBatch;

@Component("paymentBatchProcessor")
public class PaymentBatchProcessor<T extends AbstractACHBatch> implements ItemProcessor<AbstractACHBatch, T> {

	@Override
	public T process(AbstractACHBatch item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
