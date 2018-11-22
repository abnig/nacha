package com.nacha.batch.beanconfig;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.nacha")
@EnableBatchProcessing
public class BatchTransactionReaderStepBeanConfig {

}
