package com.nacha.batch.reader.fieldmapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.nacha.domain.batch.PaymentBatch;
/**
 * 
 * 
Indicator, Service class code, Chase Acct, SEC Code, Entry description, Effective entry date, Batch credit amount, Batch debit amount, Batch number, Trxn in Batch 
5, 220, 632306437, PPD, Payroll, 181105,100,0,3,1 
 
 * @author abnig19
 *
 */
@Component("reversalBatchFieldSetMapper")
public class  ReversalBatchFieldSetMapper implements FieldSetMapper<PaymentBatch> {

	@Override
	public PaymentBatch mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		return null;
	}

}