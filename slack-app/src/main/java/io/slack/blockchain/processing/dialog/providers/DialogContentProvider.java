package io.slack.blockchain.processing.dialog.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;

@Component
public class DialogContentProvider {
	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	public <S> DialogContent<S> provide(final DialogIdentityPayload dialogIdentityPayload, final String payload,
			final Class<S> ClassOfSubmission) {
		return new DialogContent<S>(dialogIdentityPayload, dialogPayloadParser.parseSubmission(payload, ClassOfSubmission));
	}
}
