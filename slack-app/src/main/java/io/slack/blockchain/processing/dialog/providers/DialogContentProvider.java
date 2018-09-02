package io.slack.blockchain.processing.dialog.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;

@Component
public class DialogContentProvider {
	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	public <S> DialogContent<S> provide(final String payload, final Class<S> classOfSubmission) {
		return new DialogContent<S>(dialogPayloadParser.parseIdentity(payload),
				dialogPayloadParser.parseSubmission(payload, classOfSubmission));
	}
}
