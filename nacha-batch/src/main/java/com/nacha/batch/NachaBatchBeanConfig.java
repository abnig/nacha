package com.nacha.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.nacha.batch.reader.fieldmapper.ACHBatchFieldSetMapperFactory;
import com.nacha.batch.reader.fieldmapper.FileHeaderFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.PaymentBatchFieldSetMapper;
import com.nacha.batch.reader.fieldmapper.ReversalBatchFieldSetMapper;

@Configuration
@ComponentScan("com.nacha")
public class NachaBatchBeanConfig {
	
	@Autowired
	private FileHeaderFieldSetMapper fileHeaderFieldSetMapper;
	
	@Autowired
	private  PaymentBatchFieldSetMapper paymentBatchFieldSetMapper;
	
	@Autowired
	private ReversalBatchFieldSetMapper reversalBatchFieldSetMapper;
	
	@Autowired
	private ACHBatchFieldSetMapperFactory achBatchFieldSetMapperFactory;

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
	public Map<String, LineTokenizer> fieldSetMapperMap(FileHeaderFieldSetMapper fileHeaderFieldSetMapper, PaymentBatchFieldSetMapper paymentBatchFieldSetMapper,
			ReversalBatchFieldSetMapper reversalBatchFieldSetMapper) {
		Map<String, FieldSetMapper> map = new HashMap<String, FieldSetMapper>();
		map.put("1*", fileHeaderFieldSetMapper);
		map.put("5*", payment);		
		map.put("6*", delimitedLineTokenizer);				
	}
	
	/*
	<bean id="orderFileLineMapper" class="org.spr...PatternMatchingCompositeLineMapper">
	  <property name="tokenizers">
	    <map>
	      <entry key="USER*" value-ref="userTokenizer" />
	      <entry key="LINEA*" value-ref="lineATokenizer" />
	      <entry key="LINEB*" value-ref="lineBTokenizer" />
	    </map>
	  </property>
	  <property name="fieldSetMappers">
	    <map>
	      <entry key="USER*" value-ref="userFieldSetMapper" />
	      <entry key="LINE*" value-ref="lineFieldSetMapper" />
	    </map>
	  </property>
	</bean>
	 */
	/*
	@Bean
	public FlatFileItemReader<Employee> reader()
	{
	    //Create reader instance
	    FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
	     
	    //Set input file location
	    reader.setResource(new FileSystemResource("input/inputData.csv"));
	     
	    //Set number of lines to skips. Use it if file has header rows.
	    reader.setLinesToSkip(1);  
	     
	    //Configure how each line will be parsed and mapped to different values
	    reader.setLineMapper(new DefaultLineMapper() {
	        {
	            //3 columns in each row
	            setLineTokenizer(new DelimitedLineTokenizer() {
	                {
	                    setNames(new String[] { "id", "firstName", "lastName" });
	                }
	            });
	            //Set values in Employee class
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
	                {
	                    setTargetType(Employee.class);
	                }
	            });
	        }
	    });
	    return reader;
	}
	*/
}
