package com.nacha.batch.beanconfig;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import com.nacha.batch.reader.BatchTransactionItemReader;
import com.nacha.batch.reader.fieldmapper.AbstractACHBatchFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.AbstractACHTransactionFieldSetMapper;
import com.nacha.batch.writer.ACHBatchWriter;
import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.composite.BatchTransactionCompositeObject;
import com.nacha.domain.file.FileHeader;
import com.nacha.domain.transaction.AbstractACHTransaction;

@Configuration
@ComponentScan("com.nacha")
@EnableBatchProcessing
@Import(value = FileHeaderRecordStepBeanConfig.class)
public class BatchTransactionReaderStepBeanConfig {

	@Autowired
	private AbstractACHBatchFieldSetMapper abstractACHBatchFieldSetMapper;

	@Autowired
	private AbstractACHTransactionFieldSetMapper abstractACHTransactionFieldSetMapper;

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

	@Autowired
	private DelimitedLineTokenizer headerRecordLineTokenizer;

	@Autowired
	private DelimitedLineTokenizer batchRecordLineTokenizer;

	@Autowired
	private DelimitedLineTokenizer transactionRecordLineTokenizer;

	@Autowired
	private BatchTransactionItemReader batchTransactionItemReader;

	@Autowired
	private ACHBatchWriter<BatchTransactionCompositeObject> achBatchWriter;
	
	@Bean
	@StepScope
	public FlatFileItemReader<AbstractACHBatch> csvBatchItemReader() {
		FlatFileItemReader<AbstractACHBatch> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ClassPathResource("file.txt"));
		csvFileReader.setLinesToSkip(1);
		DefaultLineMapper<AbstractACHBatch> studentLineMapper = new DefaultLineMapper<>();
		studentLineMapper.setFieldSetMapper(abstractACHBatchFieldSetMapper);
		studentLineMapper.setLineTokenizer(batchRecordLineTokenizer);
		csvFileReader.setLineMapper(studentLineMapper);
		return csvFileReader;
	}

	@Bean
	@StepScope
	public FlatFileItemReader<AbstractACHTransaction> csvTransactionItemReader() {
		FlatFileItemReader<AbstractACHTransaction> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ClassPathResource("file.txt"));
		csvFileReader.setLinesToSkip(1);
		DefaultLineMapper<AbstractACHTransaction> studentLineMapper = new DefaultLineMapper<>();
		studentLineMapper.setFieldSetMapper(abstractACHTransactionFieldSetMapper);
		studentLineMapper.setLineTokenizer(transactionRecordLineTokenizer);
		csvFileReader.setLineMapper(studentLineMapper);
		return csvFileReader;
	}

	@Bean
	@StepScope
	public SingleItemPeekableItemReader<AbstractACHTransaction> singleItemPeekableItemReader(
			ItemReader<AbstractACHTransaction> csvTransactionItemReader) {
		SingleItemPeekableItemReader<AbstractACHTransaction> singleItemPeekableItemReader = new SingleItemPeekableItemReader<AbstractACHTransaction>();
		singleItemPeekableItemReader.setDelegate(csvTransactionItemReader);
		return singleItemPeekableItemReader;
	}

	@Bean
	public Step batchReaderStep(ItemReader<BatchTransactionCompositeObject> csvBatchItemReader,
			ItemWriter<BatchTransactionCompositeObject> fileHeaderPassThroughItemWriter) {
		return steps.get("batchReaderStep").<BatchTransactionCompositeObject, BatchTransactionCompositeObject>chunk(10)
				.reader(batchTransactionItemReader).writer(achBatchWriter).build();
	}

}
