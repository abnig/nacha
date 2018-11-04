package com.nacha.domain.transaction.validator;

import com.nacha.domain.transaction.AbstractACHTransaction;

public interface TransactionValidator<T extends AbstractACHTransaction> {

	void validateTransaction(T transaction);
}
