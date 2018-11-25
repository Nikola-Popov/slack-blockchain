package io.slack.blockchain.domain.dialog.contents;

import lombok.Data;

@Data
public class DialogContent {
	private DialogIdentityPayload dialogIdentityPayload;

	public DialogContent(final DialogIdentityPayload dialogIdentityPayload) {
		this.dialogIdentityPayload = dialogIdentityPayload;
	}
}
