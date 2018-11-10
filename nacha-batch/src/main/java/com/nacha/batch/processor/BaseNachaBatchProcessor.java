package com.nacha.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nacha.batch.processor.classifier.BatchClassifier;
import com.nacha.domain.batch.AbstractACHBatch;

@Component("baseNachaBatchProcessor")
public class BaseNachaBatchProcessor<P extends AbstractACHBatch> implements ItemProcessor<AbstractACHBatch, P> {

	@Autowired
	private BatchClassifier batchClassifier;

	@Override
	public P process(AbstractACHBatch item) throws Exception {
		ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch> itemProcessor = batchClassifier.classify(item.getAchFileBatchDescription());
		return itemProcessor.process(item);
	}


}
