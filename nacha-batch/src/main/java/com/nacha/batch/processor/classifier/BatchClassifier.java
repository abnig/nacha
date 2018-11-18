package com.nacha.batch.processor.classifier;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.Classifier;
import org.springframework.stereotype.Component;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.enums.ACHFileBatchDescription;

@Component("batchClassifier")
public final class BatchClassifier implements Classifier<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5983997974367581384L;

	@Autowired
	private final Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> itemProcessorMap;

	public BatchClassifier(Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> itemProcessorMap) {
		super();
		this.itemProcessorMap = itemProcessorMap;
	}

	@Override
	public ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch> classify(ACHFileBatchDescription achFileBatchDescription) {
		if (itemProcessorMap.containsKey(achFileBatchDescription)) {
			return itemProcessorMap.get(achFileBatchDescription);
		} else {
			throw new IllegalArgumentException("No processor found for  '" + achFileBatchDescription + "'");
		}
	}

	public Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> getItemProcessorMap() {
		return itemProcessorMap;
	}
	
}