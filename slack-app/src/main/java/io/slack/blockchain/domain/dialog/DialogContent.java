package io.slack.blockchain.domain.dialog;

import lombok.Data;

@Data
public class DialogContent<S> {
	private DialogIdentityPayload dialogIdentityPayload;
	private S submission;

	public DialogContent(final DialogIdentityPayload dialogIdentityPayload, final S submission) {
		this.dialogIdentityPayload = dialogIdentityPayload;
		this.submission = submission;
	}
}
