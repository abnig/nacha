package com.nacha.batch.beanconfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nacha.batch.processor.PaymentBatchProcessor;
import com.nacha.batch.processor.ReversalBatchProcessor;
import com.nacha.batch.processor.classifier.BatchClassifier;
import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.enums.ACHFileBatchDescription;
import com.nacha.domain.file.FileHeader;
import com.nacha.domain.transaction.AbstractACHTransaction;

@Configuration
@ComponentScan("com.nacha")
public class NachaBatchBeanConfig {
	
	@Autowired
	private PaymentBatchProcessor paymentBatchProcessor;
	
	@Autowired
	private ReversalBatchProcessor reversalBatchProcessor;
	
	@Autowired
	private BatchClassifier batchClassifier;
	
	@Bean
	public DelimitedLineTokenizer delimitedLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setStrict(Boolean.TRUE);
		return delimitedLineTokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer  headerRecordLineTokenizer(DelimitedLineTokenizer delimitedLineTokenizer) {
		delimitedLineTokenizer.setNames("indicator,fileId,creationDate,creationTime,totalTransactionCount,totalCreditAmount,totalDebitAmount,batchCount".split(","));
		return delimitedLineTokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer  batchRecordLineTokenizer(DelimitedLineTokenizer delimitedLineTokenizer) {
		delimitedLineTokenizer.setNames("indicator,achServiceClassCode,accountNumber,achStandardEntryClassCode,achFileBatchDescription,effectiveEntryDate,totalCreditAmount,totalDebitAmount,batchNumber,numberOfTransactionsInBatch".split(","));
		return delimitedLineTokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer transactionRecordLineTokenizer(DelimitedLineTokenizer delimitedLineTokenizer) {
		delimitedLineTokenizer.setNames("indicator, achTransactionCode, routingNumber, accountNumber, transactionAmount, identificationNumber, payeeName, transactionId, addenda".split(","));
		return delimitedLineTokenizer;
	}
	
	@Bean
	public Map<String, LineTokenizer> tokenizerMap(DelimitedLineTokenizer  headerRecordLineTokenizer, DelimitedLineTokenizer  batchRecordLineTokenizer, DelimitedLineTokenizer transactionRecordLineTokenizer){
		Map<String, LineTokenizer> map = new HashMap<String, LineTokenizer>();
		map.put("1*", headerRecordLineTokenizer);
		map.put("5*", batchRecordLineTokenizer);		
		map.put("6*", transactionRecordLineTokenizer);		
		return map;
	}
	
	@Bean
	public BeanWrapperFieldSetMapper<FileHeader> fileHeaderFieldSetMapper() {
		BeanWrapperFieldSetMapper<FileHeader> fileHeaderFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fileHeaderFieldSetMapper.setTargetType(FileHeader.class);
		return fileHeaderFieldSetMapper;
	}
	
	@Bean
	public BeanWrapperFieldSetMapper<AbstractACHBatch> abstractACHBatchFieldSetMapper() {
		BeanWrapperFieldSetMapper<AbstractACHBatch> abstractACHBatchFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		abstractACHBatchFieldSetMapper.setTargetType(AbstractACHBatch.class);
		return abstractACHBatchFieldSetMapper;
	}
	
	@Bean
	public BeanWrapperFieldSetMapper<AbstractACHTransaction> abstractACHTransactionFieldSetMapper() {
		BeanWrapperFieldSetMapper<AbstractACHTransaction> abstractACHTransactionFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		abstractACHTransactionFieldSetMapper.setTargetType(AbstractACHTransaction.class);
		return abstractACHTransactionFieldSetMapper;
	}
	
	@Bean
	public Map<String, FieldSetMapper<?>> fieldSetMapperMap(BeanWrapperFieldSetMapper<FileHeader> fileHeaderFieldSetMapper, 
			BeanWrapperFieldSetMapper<AbstractACHBatch> abstractACHBatchFieldSetMapper, 
			BeanWrapperFieldSetMapper<AbstractACHTransaction> abstractACHTransactionFieldSetMapper){
		Map<String, FieldSetMapper<?>> map = new HashMap<String, FieldSetMapper<?>>();
		map.put("1*", fileHeaderFieldSetMapper);
		map.put("5*", abstractACHBatchFieldSetMapper);		
		map.put("6*", abstractACHTransactionFieldSetMapper);		
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper(Map<String, LineTokenizer> tokenizerMap, Map<String, LineTokenizer> fieldSetMapperMap) {
		PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper = new PatternMatchingCompositeLineMapper<>();
		patternMatchingCompositeLineMapper.setTokenizers(tokenizerMap);
		patternMatchingCompositeLineMapper.setFieldSetMappers(fieldSetMapperMap);
		return patternMatchingCompositeLineMapper;
	}
	
	@Bean
	public Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ?>> itemProcessorMap(PaymentBatchProcessor paymentBatchProcessor, 
			ReversalBatchProcessor reversalBatchProcessor) {
		Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ?>> itemProcessorMap = new HashMap<>();
		itemProcessorMap.put(ACHFileBatchDescription.PAYROLL_PAYMENT, paymentBatchProcessor);
		itemProcessorMap.put(ACHFileBatchDescription.REVERSAL, reversalBatchProcessor);
		return itemProcessorMap;
	}
}
