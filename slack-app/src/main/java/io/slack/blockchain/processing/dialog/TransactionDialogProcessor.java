package io.slack.blockchain.processing.dialog;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.processing.dialog.DialogProcessor;

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
