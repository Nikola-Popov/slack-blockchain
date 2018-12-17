package io.slack.blockchain.domain.dialog.contents;

import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDialogContent extends DialogContent<TransactionDialogSubmission> {
	private final TransactionDialogSubmission transactionDialogSubmission;

	public TransactionDialogContent(DialogIdentityPayload dialogIdentityPayload,
			TransactionDialogSubmission transactionDialogSubmission) {
		super(dialogIdentityPayload);
		this.transactionDialogSubmission = transactionDialogSubmission;
	}

	@Override
	public TransactionDialogSubmission getDialogSubmission() {
		return transactionDialogSubmission;
	}
}
