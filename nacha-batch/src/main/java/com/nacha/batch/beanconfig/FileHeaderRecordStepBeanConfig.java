package com.nacha.batch.beanconfig;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nacha.batch.reader.fieldmapper.FileHeaderFieldSetMapper;
import com.nacha.domain.file.FileHeader;

@Configuration
@ComponentScan("com.nacha")
@EnableBatchProcessing
public class FileHeaderRecordStepBeanConfig {

	@Autowired
	private FileHeaderFieldSetMapper fileHeaderFieldSetMapper;
	
	@SuppressWarnings("unused")
	@Autowired
	private ItemWriter<FileHeader> fileHeaderPassThroughItemWriter;

	@Autowired
	private FieldSetMapper<Void> ignoreFieldSetMapper;

	@SuppressWarnings("unused")
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public DelimitedLineTokenizer headerRecordLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setStrict(Boolean.TRUE);
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setNames(
				"indicator,fileId,creationDate,creationTime,totalTransactionCount,totalCreditAmount,totalDebitAmount,batchCount"
						.split(","));
		return delimitedLineTokenizer;
	}

	@Bean
	public DelimitedLineTokenizer batchRecordLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setStrict(Boolean.TRUE);
		delimitedLineTokenizer.setNames(
				"indicator,achServiceClassCode,accountNumber,achStandardEntryClassCode,achFileBatchDescription,effectiveEntryDate,totalCreditAmount,totalDebitAmount,batchNumber,numberOfTransactionsInBatch"
						.split(","));
		return delimitedLineTokenizer;
	}

	@Bean
	public DelimitedLineTokenizer transactionRecordLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setStrict(Boolean.TRUE);
		delimitedLineTokenizer.setNames(
				"indicator, achTransactionCode, routingNumber, accountNumber, transactionAmount, identificationNumber, payeeName, transactionId, addenda"
						.split(","));
		return delimitedLineTokenizer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public PatternMatchingCompositeLineMapper fileHeaderReaderStepPatternMatchingCompositeLineMapper(
			DelimitedLineTokenizer headerRecordLineTokenizer, DelimitedLineTokenizer batchRecordLineTokenizer,
			DelimitedLineTokenizer transactionRecordLineTokenizer) {

		Map<String, FieldSetMapper<?>> map1 = new LinkedHashMap<String, FieldSetMapper<?>>();
		map1.put("1*", this.fileHeaderFieldSetMapper);
		map1.put("5*", this.ignoreFieldSetMapper);
		map1.put("6*", this.ignoreFieldSetMapper);

		Map<String, LineTokenizer> map = new LinkedHashMap<String, LineTokenizer>();
		map.put("1*", headerRecordLineTokenizer);
		map.put("5*", batchRecordLineTokenizer);
		map.put("6*", transactionRecordLineTokenizer);		

		PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper = new PatternMatchingCompositeLineMapper<>();
		patternMatchingCompositeLineMapper.setTokenizers(map);
		patternMatchingCompositeLineMapper.setFieldSetMappers(map1);
		return patternMatchingCompositeLineMapper;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ItemReader<FileHeader> csvFileHeaderItemReader(
			PatternMatchingCompositeLineMapper fileHeaderReaderStepPatternMatchingCompositeLineMapper) {
		FlatFileItemReader<FileHeader> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ClassPathResource("file.txt"));
		csvFileReader.setLineMapper(fileHeaderReaderStepPatternMatchingCompositeLineMapper);
		return csvFileReader;
	}

	@Bean
	public Step headerReaderStep(ItemReader<FileHeader> csvFileHeaderItemReader,
			ItemWriter<FileHeader> fileHeaderPassThroughItemWriter) {
		return steps.get("fileHeaderReaderStep").<FileHeader, FileHeader>chunk(10).reader(csvFileHeaderItemReader)
				.writer(fileHeaderPassThroughItemWriter).build();
	}

}
