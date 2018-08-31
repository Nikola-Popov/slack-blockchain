package io.slack.blockchain.interactive.components.dialogs.parsers;

import static io.slack.blockchain.interactive.components.dialogs.parsers.constants.SubmittedDialogConstants.CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.parsers.constants.SubmittedDialogConstants.RESPONSE_URL;
import static io.slack.blockchain.interactive.components.dialogs.parsers.constants.SubmittedDialogConstants.SUBMISSION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import io.slack.blockchain.commons.services.GsonJsonService;

@Component
public class DialogPayloadParser {
	@Autowired
	private GsonJsonService gsonJsonService;

	public String parseCallbackId(final String payload) {
		return parseAsJsonObject(payload).get(CALLBACK_ID).getAsString();
	}

	public String parseResponseUrl(final String payload) {
		return parseAsJsonObject(payload).get(RESPONSE_URL).getAsString();
	}

	public <T> T parseSubmission(final String payload, final Class<T> classOfT) {
		return gsonJsonService.fromJson(parseAsJsonObject(payload).get(SUBMISSION).getAsJsonObject(), classOfT);
	}

	private JsonObject parseAsJsonObject(final String payload) {
		return gsonJsonService.parse(payload).getAsJsonObject();
	}
}
