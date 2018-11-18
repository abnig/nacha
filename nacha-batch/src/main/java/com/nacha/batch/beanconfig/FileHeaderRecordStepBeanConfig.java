package com.nacha.batch.beanconfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nacha.batch.reader.fieldmapper.AbstractACHBatchFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.AbstractACHTransactionFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.FileHeaderFieldSetMapper;
import com.nacha.domain.file.FileHeader;

@Configuration
@ComponentScan("com.nacha")
@EnableBatchProcessing
public class FileHeaderRecordStepBeanConfig {

	@Autowired
	private FileHeaderFieldSetMapper fileHeaderFieldSetMapper;

	@Autowired
	private ItemWriter<FileHeader> fileHeaderPassThroughItemWriter;
	
	@Autowired
	private FieldSetMapper<String> ignoreFieldSetMapper; 
	
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public DelimitedLineTokenizer headerRecordLineTokenizer() {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setStrict(Boolean.TRUE);
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setNames("indicator,fileId,creationDate,creationTime,totalTransactionCount,totalCreditAmount,totalDebitAmount,batchCount".split(","));
		return delimitedLineTokenizer;
	}

	@Bean
	public LineMapper<FileHeader> fileHeaderLineMapper(DelimitedLineTokenizer headerRecordLineTokenizer,
			FileHeaderFieldSetMapper fileHeaderFieldSetMapper) {
		DefaultLineMapper<FileHeader> studentLineMapper = new DefaultLineMapper<>();
		studentLineMapper.setLineTokenizer(headerRecordLineTokenizer);
		studentLineMapper.setFieldSetMapper(fileHeaderFieldSetMapper);
		return studentLineMapper;
	}
	
	@Bean
	public Map<String, FieldSetMapper<?>> fileHeaderReaderStepFieldSetMapperMap(FileHeaderFieldSetMapper fileHeaderFieldSetMapper) {
		Map<String, FieldSetMapper<?>> map = new HashMap<String, FieldSetMapper<?>>();
		map.put("1*", fileHeaderFieldSetMapper);		
		map.put("5*", this.ignoreFieldSetMapper);
		map.put("6*", this.ignoreFieldSetMapper);
		return map;
	}

	@Bean
	public Map<String, LineTokenizer> fileHeaderReaderStepTokenizerMap(DelimitedLineTokenizer headerRecordLineTokenizer, DelimitedLineTokenizer transactionRecordLineTokenizer) {
		Map<String, LineTokenizer> map = new HashMap<String, LineTokenizer>();
		map.put("1*", headerRecordLineTokenizer);
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public PatternMatchingCompositeLineMapper fileHeaderReaderStepPatternMatchingCompositeLineMapper(
			Map<String, LineTokenizer> fileHeaderReaderStepTokenizerMap, Map<String, FieldSetMapper<?>> fileHeaderReaderStepFieldSetMapperMap) {
		PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper = new PatternMatchingCompositeLineMapper<>();
		patternMatchingCompositeLineMapper.setTokenizers(fileHeaderReaderStepTokenizerMap);
		patternMatchingCompositeLineMapper.setFieldSetMappers(fileHeaderReaderStepFieldSetMapperMap);
		return patternMatchingCompositeLineMapper;
	}

	@Bean
	public ItemReader<FileHeader> csvFileHeaderItemReader(PatternMatchingCompositeLineMapper fileHeaderReaderStepPatternMatchingCompositeLineMapper) {
		FlatFileItemReader<FileHeader> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ClassPathResource("file.txt"));
		csvFileReader.setLineMapper(fileHeaderReaderStepPatternMatchingCompositeLineMapper);
		return csvFileReader;
	}

	@Bean
	public Step headerReaderStep(ItemReader<FileHeader> csvFileHeaderItemReader, ItemWriter<FileHeader> fileHeaderPassThroughItemWriter) {
		return steps.get("fileHeaderReaderStep").<FileHeader, FileHeader>chunk(10)
				.reader(csvFileHeaderItemReader).writer(fileHeaderPassThroughItemWriter).build();
	}

}
