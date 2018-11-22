package com.nacha.batch.reader;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.nacha.domain.file.AbstractACHFile;
import com.nacha.domain.file.FileHeader;

public class BatchTransactionItemReader implements ItemReader<AbstractACHFile>, StepExecutionListener {
	
	private FileHeader fileHeader;

	@Override
	public AbstractACHFile read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}



}
