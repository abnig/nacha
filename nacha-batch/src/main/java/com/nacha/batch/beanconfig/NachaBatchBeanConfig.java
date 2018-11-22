package com.nacha.batch.beanconfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import com.nacha.batch.processor.PaymentBatchProcessor;
import com.nacha.batch.processor.ReversalBatchProcessor;
import com.nacha.batch.processor.classifier.BatchClassifier;
import com.nacha.batch.reader.fieldmapper.AbstractACHBatchFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.AbstractACHTransactionFieldSetMapper;
import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.composite.BatchTransactionCompositeObject;
import com.nacha.domain.enums.ACHFileBatchDescription;

@Configuration
@ComponentScan("com.nacha")
@EnableBatchProcessing
@Import(value = FileHeaderRecordStepBeanConfig.class)
public class NachaBatchBeanConfig {

	@Autowired
	private PaymentBatchProcessor paymentBatchProcessor;

	@Autowired
	private ReversalBatchProcessor reversalBatchProcessor;

	@Autowired
	private BatchClassifier batchClassifier;

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private AbstractACHBatchFieldSetMapper abstractACHBatchFieldSetMapper;

	@Autowired
	private AbstractACHTransactionFieldSetMapper abstractACHTransactionFieldSetMapper;

	@Bean
	public BlockingQueue<BatchTransactionCompositeObject> batchQueue() {
		BlockingQueue<BatchTransactionCompositeObject> batchQueue = new LinkedBlockingQueue<>();
		return batchQueue;
	}

	@Bean
	public Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> itemProcessorMap(
			PaymentBatchProcessor paymentBatchProcessor, ReversalBatchProcessor reversalBatchProcessor) {
		Map<ACHFileBatchDescription, ItemProcessor<AbstractACHBatch, ? super AbstractACHBatch>> itemProcessorMap = new HashMap<>();
		itemProcessorMap.put(ACHFileBatchDescription.PAYROLL_PAYMENT, paymentBatchProcessor);
		itemProcessorMap.put(ACHFileBatchDescription.REVERSAL, reversalBatchProcessor);
		return itemProcessorMap;
	}

	/*
	 * @Bean public BeanWrapperFieldSetMapper<FileHeader> fileHeaderFieldSetMapper()
	 * { BeanWrapperFieldSetMapper<FileHeader> fileHeaderFieldSetMapper = new
	 * BeanWrapperFieldSetMapper<>();
	 * fileHeaderFieldSetMapper.setTargetType(FileHeader.class); return
	 * fileHeaderFieldSetMapper; }
	 * 
	 * @Bean public BeanWrapperFieldSetMapper<AbstractACHBatch>
	 * abstractACHBatchFieldSetMapper() {
	 * BeanWrapperFieldSetMapper<AbstractACHBatch> abstractACHBatchFieldSetMapper =
	 * new BeanWrapperFieldSetMapper<>();
	 * abstractACHBatchFieldSetMapper.setTargetType(AbstractACHBatch.class); return
	 * abstractACHBatchFieldSetMapper; }
	 * 
	 * @Bean public BeanWrapperFieldSetMapper<AbstractACHTransaction>
	 * abstractACHTransactionFieldSetMapper() {
	 * BeanWrapperFieldSetMapper<AbstractACHTransaction>
	 * abstractACHTransactionFieldSetMapper = new BeanWrapperFieldSetMapper<>();
	 * abstractACHTransactionFieldSetMapper.setTargetType(AbstractACHTransaction.
	 * class); return abstractACHTransactionFieldSetMapper; }

	@Bean
	public Map<String, FieldSetMapper<?>> fieldSetMapperMap(AbstractACHBatchFieldSetMapper abstractACHBatchFieldSetMapper,
			AbstractACHTransactionFieldSetMapper abstractACHTransactionFieldSetMapper) {
		Map<String, FieldSetMapper<?>> map = new HashMap<String, FieldSetMapper<?>>();
		map.put("5*", abstractACHBatchFieldSetMapper);
		map.put("6*", abstractACHTransactionFieldSetMapper);
		return map;
	}

	@Bean
	public Map<String, LineTokenizer> tokenizerMap(DelimitedLineTokenizer batchRecordLineTokenizer, DelimitedLineTokenizer transactionRecordLineTokenizer) {
		Map<String, LineTokenizer> map = new HashMap<String, LineTokenizer>();
		map.put("5*", batchRecordLineTokenizer);
		map.put("6*", transactionRecordLineTokenizer);
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper(
			Map<String, LineTokenizer> tokenizerMap, Map<String, FieldSetMapper<?>> fieldSetMapperMap) {
		PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper = new PatternMatchingCompositeLineMapper<>();
		patternMatchingCompositeLineMapper.setTokenizers(tokenizerMap);
		patternMatchingCompositeLineMapper.setFieldSetMappers(fieldSetMapperMap);
		return patternMatchingCompositeLineMapper;
	}

	@Bean
	public ItemReader<BatchTransactionCompositeObject> csvFileItemReader(
			PatternMatchingCompositeLineMapper patternMatchingCompositeLineMapper) {
		FlatFileItemReader<BatchTransactionCompositeObject> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setLinesToSkip(2);
		csvFileReader.setResource(new ClassPathResource("file.txt"));
		csvFileReader.setLineMapper(patternMatchingCompositeLineMapper);
		return csvFileReader;
	}

	@Bean
	protected Step step1(ItemReader<BatchTransactionCompositeObject> csvFileItemReader,
			ItemWriter<? super BatchTransactionCompositeObject> achBatchWriter) {
		return steps.get("step1").<BatchTransactionCompositeObject, BatchTransactionCompositeObject>chunk(10)
				.reader(csvFileItemReader).writer(achBatchWriter).build();
		// writer(achBatchWriter).build();
	}
	 */
	@Bean
	public Job job(Step headerReaderStep) {
		return jobs.get("job").start(headerReaderStep).build();
	}

	@Bean
	public MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean() throws Exception {
		MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean(new ResourcelessTransactionManager());
		factoryBean.afterPropertiesSet();
		return factoryBean;
	}

	/*
	 
	  	@Bean
	public ResourcelessTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}
	  
	  
	 * @Bean public DataSource dataSource() { DriverManagerDataSource dataSource =
	 * new DriverManagerDataSource();
	 * dataSource.setDriverClassName("org.h2.Driver");
	 * dataSource.setUrl("jdbc:h2:mem:localhost;DB_CLOSE_ON_EXIT=FALSE");
	 * dataSource.setUsername("admin"); return dataSource; }
	 * 
	 * 
	 * 
	 * 
	 * @Bean public JobRepository jobRepository(MapJobRepositoryFactoryBean
	 * factoryBean) throws Exception { return (JobRepository)
	 * factoryBean.getObject(); }
	 * 
	 * 
	 * @Bean public JobRepository jobRepository() { MapJobRepositoryFactoryBean
	 * factoryBean = new MapJobRepositoryFactoryBean(new
	 * ResourcelessTransactionManager()); try { JobRepository jobRepository =
	 * factoryBean.getObject(); return jobRepository; } catch (Exception e) {
	 * e.printStackTrace(); return null; } }
	 * 
	 * @Bean public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
	 * SimpleJobLauncher launcher = new SimpleJobLauncher();
	 * launcher.setJobRepository(jobRepository); return launcher; }
	 *
	 * 
	 */

}
