package com.nacha.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.nacha.domain.batch.AbstractACHBatch;

public class ACHBatchWriter implements ItemWriter<AbstractACHBatch> {

	@Override
	public void write(List<? extends AbstractACHBatch> items) throws Exception {
		
		// TODO Auto-generated method stub
		
	}

}
