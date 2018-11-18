package com.nacha.batch.writer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nacha.domain.composite.BatchTransactionCompositeObject;

@Component("achBatchWriter")
public class ACHBatchWriter<T extends BatchTransactionCompositeObject> implements ItemWriter<T> {

	@Autowired
	private BlockingQueue<BatchTransactionCompositeObject> batchQueue;
	
	@Override
	public void write(List<? extends T> items) throws Exception {
		this.batchQueue.addAll(items);
	}

}
