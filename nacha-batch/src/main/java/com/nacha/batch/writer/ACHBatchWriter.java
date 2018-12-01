package com.nacha.batch.writer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nacha.domain.composite.BatchTransactionCompositeObject;

@Component("achBatchWriter")
@StepScope
public class ACHBatchWriter<T extends BatchTransactionCompositeObject> implements ItemWriter<T>, ItemWriteListener<T> {

	private final static Logger logger = LoggerFactory.getLogger(ACHBatchWriter.class);
	
	@Autowired
	private BlockingQueue<BatchTransactionCompositeObject> batchQueue;
	
	@Override
	public void write(List<? extends T> items) throws Exception {
		this.batchQueue.addAll(items);
	}

	@Override
	public void beforeWrite(List<? extends T> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWrite(List<? extends T> items) {
		for(BatchTransactionCompositeObject obj : items) {
			logger.info(obj.toString());
		}
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends T> items) {
		// TODO Auto-generated method stub
		
	}

}
