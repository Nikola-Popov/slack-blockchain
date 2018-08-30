package io.slack.blockchain.processing;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.domain.processing.ProcessingResult;

public class TransactionDialogProcessor implements DialogProcessor {
	private TransactionDialogSubmission transactionDialogSubmission;

	public TransactionDialogProcessor(final TransactionDialogSubmission transactionDialogSubmission) {
		this.transactionDialogSubmission = transactionDialogSubmission;
	}

	@Override
	public ProcessingResult process() {
		// TODO Auto-generated method stub
		return null;
	}

}
