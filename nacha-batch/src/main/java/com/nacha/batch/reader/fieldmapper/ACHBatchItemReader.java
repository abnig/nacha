package com.nacha.batch.reader.fieldmapper;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.support.SingleItemPeekableItemReader;

import com.nacha.domain.composite.BatchTransactionCompositeObject;
import com.nacha.domain.transaction.AbstractACHTransaction;

public class ACHBatchItemReader implements ItemReader<BatchTransactionCompositeObject> {

	private Set<AbstractACHTransaction> transactionSet = null;

	private SingleItemPeekableItemReader<FieldSet> reader;

	@Override
	public BatchTransactionCompositeObject read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		transactionSet = new LinkedHashSet<AbstractACHTransaction>();
		AbstractACHTransaction line = null;
		AbstractACHTransaction prevElement = null;
		boolean exit = false;

		while (!exit && (line = (AbstractACHTransaction) this.reader.peek()) != null) {

			if (prevElement != null && line.getIndicator().equals(prevElement.getIndicator())) {
				// ..
			} else {
				// ..
				exit = true;
			}
		}

		if (!exit) {
			reader.read();
			prevElement = line;
			transactionSet.add(line);
		}
		
		
		
		return null;
	}

}
