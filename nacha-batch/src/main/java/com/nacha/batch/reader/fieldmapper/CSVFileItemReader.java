package com.nacha.batch.reader.fieldmapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.file.CSVFile;
import com.nacha.domain.file.FileHeader;
import com.nacha.domain.transaction.AbstractACHTransaction;

public class CSVFileItemReader implements ItemReader<CSVFile> {
	
	private static Log log = LogFactory.getLog(CSVFileItemReader.class);

	private CSVFile csvFile;

	private boolean recordFinished;
	
	private ItemReader<FieldSet> fieldSetReader;
	
	@Autowired
	private BeanWrapperFieldSetMapper<FileHeader> fileHeaderFieldSetMapper;

	@Autowired
	private BeanWrapperFieldSetMapper<AbstractACHBatch> abstractACHBatchFieldSetMapper;
	
	@Autowired
	private BeanWrapperFieldSetMapper<AbstractACHTransaction> abstractACHTransactionFieldSetMapper;

	@Override
	public CSVFile read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException { 
		recordFinished = false;

		while (!recordFinished) {
			process(fieldSetReader.read());
		}

		log.info("Mapped: " + csvFile);

		CSVFile result = csvFile;
		csvFile = null;

		return result;
	}
	
	private void process(FieldSet fieldSet) throws Exception {
		
	}

}
