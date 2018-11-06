package com.nacha.batch;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.enums.ACHFileBatchDescription;

public class BatchTypeClassifier<T  extends  AbstractACHBatch> implements Classifier<ACHFileBatchDescription, ItemProcessor<T, ? super AbstractACHBatch>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3168141333330775606L;
	
	private Map<ACHFileBatchDescription, ItemProcessor<T, ? super AbstractACHBatch>> itemProcessorMap;

	@Override
	public ItemProcessor<T, ? super AbstractACHBatch> classify(ACHFileBatchDescription achFileBatchDesc) {
		if(itemProcessorMap.containsKey(achFileBatchDesc)) {
			return itemProcessorMap.get(achFileBatchDesc);
		}
		else
			throw new IllegalArgumentException("No processor found for batch type class '" + achFileBatchDesc.name() + "'");
	}

	public Map<ACHFileBatchDescription, ItemProcessor<T, ? super AbstractACHBatch>> getItemProcessorMap() {
		return itemProcessorMap;
	}

	public void setItemProcessorMap(
			Map<ACHFileBatchDescription, ItemProcessor<T, ? super AbstractACHBatch>> itemProcessorMap) {
		this.itemProcessorMap = itemProcessorMap;
	}

}