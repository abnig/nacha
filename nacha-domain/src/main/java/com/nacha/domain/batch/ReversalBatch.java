package com.nacha.domain.batch;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import com.nacha.domain.batch.validator.BatchValidator;
import com.nacha.domain.enums.ACHFileBatchDescription;
import com.nacha.domain.enums.ACHServiceClassCode;
import com.nacha.domain.enums.ACHStandardEntryClassCode;
import com.nacha.domain.transaction.ReversalTransaction;

public class ReversalBatch extends AbstractACHBatch {

	private final BigDecimal totalDebitAmount;
	private final Set<ReversalTransaction> achTransactionSet;
	private final Set<Integer> errorCodesSet;
	private BatchValidator<ReversalBatch> batchValidator;

	public ReversalBatch(AbstractACHBatch abstractACHBatch, String accountNumber,
			ACHFileBatchDescription achFileBatchDescription, ACHServiceClassCode achServiceClassCode,
			ACHStandardEntryClassCode achStandardEntryClassCode, Long batchNumber, Calendar effectiveEntryDate,
			BigDecimal totalDebitAmount, Set<ReversalTransaction> achTransactionSet, Set<Integer> errorCodesSet) {
		super(abstractACHBatch);
		this.totalDebitAmount = totalDebitAmount;
		this.achTransactionSet = achTransactionSet;
		this.errorCodesSet = errorCodesSet;
	}

	public BigDecimal getTotalDebitAmount() {
		return totalDebitAmount;
	}

	public Set<ReversalTransaction> getAchTransactionSet() {
		return achTransactionSet;
	}

	public Set<Integer> getErrorCodesSet() {
		return errorCodesSet;
	}

	@Override
	public void executeValidation() {
		this.batchValidator.validateBatch(this);
	}

}
