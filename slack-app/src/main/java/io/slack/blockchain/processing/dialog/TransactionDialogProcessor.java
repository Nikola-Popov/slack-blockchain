package io.slack.blockchain.processing.dialog;

import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.domain.processing.ProcessingResult;

public class TransactionDialogProcessor implements DialogProcessor {
	private DialogContent<TransactionDialogSubmission> dialogContent;

	public TransactionDialogProcessor(final DialogContent<TransactionDialogSubmission> dialogContent) {
		this.dialogContent = dialogContent;
	}

	@Override
	public ProcessingResult process() {
		// TODO Auto-generated method stub
		return null;
	}

}
