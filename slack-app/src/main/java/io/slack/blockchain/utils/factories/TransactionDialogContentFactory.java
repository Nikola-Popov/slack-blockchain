package io.slack.blockchain.utils.factories;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;
import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;

@Component
public class TransactionDialogContentFactory implements DialogContentFactory<TransactionDialogSubmission> {
	@Override
	public TransactionDialogContent create(final DialogIdentityPayload dialogIdentityPayload,
			final TransactionDialogSubmission transactionDialogSubmission) {
		return new TransactionDialogContent(dialogIdentityPayload, transactionDialogSubmission);
	}
}
