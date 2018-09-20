package io.slack.blockchain.interactive.components.dialogs.parsers;

import static io.slack.blockchain.interactive.components.dialogs.constants.DialogPayloadConstants.SUBMISSION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.commons.services.GsonJsonService;
import io.slack.blockchain.domain.dialog.DialogIdentityPayload;

@Component
public class DialogPayloadParser {
	@Autowired
	private GsonJsonService gsonJsonService;

	public DialogIdentityPayload parseIdentity(final String payload) {
		return gsonJsonService.fromJson(gsonJsonService.parse(payload).getAsJsonObject(), DialogIdentityPayload.class);
	}

	public <S> S parseSubmission(final String payload, Class<S> submissionClass) {
		return gsonJsonService.fromJson(gsonJsonService.parse(payload).getAsJsonObject().get(SUBMISSION),
				submissionClass);
	}
}
