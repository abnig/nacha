package com.nacha.batch.reader.fieldmapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.nacha.domain.batch.AbstractACHBatch;
import com.nacha.domain.enums.ACHFileBatchDescription;

@Component("achBatchFieldSetMapperFactory")
public class ACHBatchFieldSetMapperFactory {

	@Autowired
	private ApplicationContext applicationContext;
	
	public FieldSetMapper<? extends AbstractACHBatch> getFieldSetMapper(ACHFileBatchDescription achFileBatchDescription) {
		FieldSetMapper<? extends AbstractACHBatch> fieldSetMapper = null;
		switch(achFileBatchDescription) {
		case VENDOR_PAYMENT:
		case PAYROLL_PAYMENT:
			fieldSetMapper = applicationContext.getBean(PaymentBatchFieldSetMapper.class);
			break;
		case REVERSAL:
			fieldSetMapper = applicationContext.getBean(ReversalBatchFieldSetMapper.class);
			break;
		}
		return fieldSetMapper;
	}
}