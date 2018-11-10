package com.nacha.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.batch.PaymentBatch;

@Component("paymentBatchProcessor")
public class PaymentBatchProcessor implements ItemProcessor<AbstractACHBatch, PaymentBatch> {

	@Override
	public PaymentBatch process(AbstractACHBatch item) throws Exception {
		PaymentBatch paymentBatch = new PaymentBatch(item, null, null, null);
		
		return paymentBatch;
	}
	

}
