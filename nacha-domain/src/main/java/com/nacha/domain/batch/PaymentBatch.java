package com.nacha.domain.batch;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import com.nacha.domain.batch.validator.BatchValidator;
import com.nacha.domain.enums.ACHFileBatchDescription;
import com.nacha.domain.enums.ACHServiceClassCode;
import com.nacha.domain.enums.ACHStandardEntryClassCode;
import com.nacha.domain.transaction.PaymentTransaction;

public class PaymentBatch extends AbstractACHBatch {

	private final BigDecimal totalCreditAmount;
	private final Set<PaymentTransaction> achTransactionSet;
	private final Set<Integer> errorCodesSet;
	private BatchValidator<PaymentBatch> batchValidator;

	public PaymentBatch(AbstractACHBatch abstractACHBatch, String accountNumber,
			ACHFileBatchDescription achFileBatchDescription, ACHServiceClassCode achServiceClassCode,
			ACHStandardEntryClassCode achStandardEntryClassCode, Long batchNumber, Calendar effectiveEntryDate,
			BigDecimal totalCreditAmount, Set<PaymentTransaction> achTransactionSet, Set<Integer> errorCodesSet) {
		super(abstractACHBatch);
		this.totalCreditAmount = totalCreditAmount;
		this.achTransactionSet = achTransactionSet;
		this.errorCodesSet = errorCodesSet;
	}

	public BigDecimal getTotalCreditAmount() {
		return totalCreditAmount;
	}

	public Set<PaymentTransaction> getAchTransactionSet() {
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
