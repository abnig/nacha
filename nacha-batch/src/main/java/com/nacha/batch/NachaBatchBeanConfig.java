package com.nacha.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
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
import com.nacha.batch.reader.fieldmapper.AbstractACHBatchFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.AbstractACHTransactionFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.FileHeaderFieldSetMapper;
import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.enums.ACHFileBatchDescription;

@Configuration
@ComponentScan("com.nacha")
public class NachaBatchBeanConfig {
	
	@Autowired
	private FileHeaderFieldSetMapper fileHeaderFieldSetMapper;
	
	@Autowired
	private  AbstractACHBatchFieldSetMapper paymentBatchFieldSetMapper;
	
	@Autowired
	private PaymentBatchProcessor paymentBatchProcessor;
	
	@Autowired
	private ReversalBatchProcessor reversalBatchProcessor;
	
	@Autowired
	private BatchClassifier batchClassifier;
	
	@Autowired
	private AbstractACHTransactionFieldSetMapper abstractACHTransactionFieldSetMapper;

	@Bean
	public LineTokenizer delimitedLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setStrict(Boolean.TRUE);
		return delimitedLineTokenizer;
	}
	
	@Bean
	public Map<String, LineTokenizer> tokenizerMap(LineTokenizer delimitedLineTokenizer){
		Map<String, LineTokenizer> map = new HashMap<String, LineTokenizer>();
		map.put("1*", delimitedLineTokenizer);
		map.put("5*", delimitedLineTokenizer);		
		map.put("6*", delimitedLineTokenizer);		
		return map;
	}
	
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
