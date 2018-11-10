package com.nacha.batch;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.enums.ACHFileBatchDescription;

public class BatchTypeClassifier<T  extends  AbstractACHBatch> implements Classifier<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3168141333330775606L;
	
	private Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> itemProcessorMap;

	public Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> getItemProcessorMap() {
		return itemProcessorMap;
	}

	public void setItemProcessorMap(
			Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> itemProcessorMap) {
		this.itemProcessorMap = itemProcessorMap;
	}

	@Override
	public ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch> classify(ACHFileBatchDescription achFileBatchDesc) {
		if(itemProcessorMap.containsKey(achFileBatchDesc)) {
			return itemProcessorMap.get(achFileBatchDesc);
		}
		else
			throw new IllegalArgumentException("No processor found for batch type '" + achFileBatchDesc.name() + "'");
	}

}

