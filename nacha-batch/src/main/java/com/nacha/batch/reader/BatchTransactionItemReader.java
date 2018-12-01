package com.nacha.batch.reader;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.composite.BatchTransactionCompositeObject;
import com.nacha.domain.file.FileHeader;
import com.nacha.domain.transaction.AbstractACHTransaction;

@Component("batchTransactionItemReader")
@StepScope
public class BatchTransactionItemReader implements  ItemReader<BatchTransactionCompositeObject>, StepExecutionListener, ItemStream {
	
	private final static Logger logger = LoggerFactory.getLogger(BatchTransactionItemReader.class);
	
	private FileHeader fileHeader;
	
	@Autowired
	private FlatFileItemReader<AbstractACHBatch> csvBatchItemReader;
	
	@Autowired
	private SingleItemPeekableItemReader<AbstractACHTransaction> singleItemPeekableItemReader;
	

	@Override
	public BatchTransactionCompositeObject read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		Set<AbstractACHTransaction> transactionSet = new HashSet<>();
		AbstractACHBatch achBatch = this.csvBatchItemReader.read();
		while(this.singleItemPeekableItemReader.peek() != null) {
			transactionSet.add(this.singleItemPeekableItemReader.read());
		}
		BatchTransactionCompositeObject batchTransactionObject = new BatchTransactionCompositeObject(achBatch, transactionSet);
		return batchTransactionObject;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		if (stepExecution.getExecutionContext().containsKey("fileHeader")) {
			this.fileHeader = (FileHeader) stepExecution.getExecutionContext().get("fileHeader");
			logger.info("Got file Header record from execution context with key='fileHeader' " + fileHeader.toString());
		} else {
			logger.info("Failed to read File Header/from execution context with key='fileHeader' ");
		}
		
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		this.csvBatchItemReader.open(executionContext);
		this.singleItemPeekableItemReader.open(executionContext);
		
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws ItemStreamException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}
}
