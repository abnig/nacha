package com.nacha.domain.batch.validator;

import com.nacha.domain.batch.AbstractACHBatch;

public interface BatchValidator<T extends AbstractACHBatch> {

	void validateBatch(T t);

}
