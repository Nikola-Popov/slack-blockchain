package io.slack.blockchain.interactive.components.dialogs.parsers;

import static io.slack.blockchain.interactive.components.dialogs.parsers.constants.SubmittedDialogConstants.RESPONSE_URL;
import static io.slack.blockchain.interactive.components.dialogs.parsers.constants.SubmittedDialogConstants.SUBMISSION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import io.slack.blockchain.commons.services.GsonJsonService;

@Component
public class GenericSubmissionDialogParser {
	@Autowired
	private GsonJsonService gsonJsonService;

	public <T> T parse(final String payload, final Class<T> classOfT) {
		final JsonObject payloadAsJson = gsonJsonService.parse(payload).getAsJsonObject();
		return gsonJsonService.fromJson(payloadAsJson.get(SUBMISSION).getAsJsonObject(), classOfT);
	}

	public String parseResponseUrl(final String payload) {
		return gsonJsonService.parse(payload).getAsJsonObject().get(RESPONSE_URL).getAsString();
	}
}
