package io.slack.blockchain.utils.factories;

import io.slack.blockchain.domain.dialog.contents.DialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;

public interface DialogContentFactory<S> {
	<T extends DialogContent> T create(final DialogIdentityPayload dialogIdentityPayload, final S submission);
}
