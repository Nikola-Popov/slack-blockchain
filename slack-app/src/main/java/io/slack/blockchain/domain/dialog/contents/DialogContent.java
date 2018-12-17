package io.slack.blockchain.domain.dialog.contents;

import lombok.Data;

@Data
public abstract class DialogContent<S> {
	private DialogIdentityPayload dialogIdentityPayload;

	public DialogContent(final DialogIdentityPayload dialogIdentityPayload) {
		this.dialogIdentityPayload = dialogIdentityPayload;
	}

	public abstract S getDialogSubmission();
}
