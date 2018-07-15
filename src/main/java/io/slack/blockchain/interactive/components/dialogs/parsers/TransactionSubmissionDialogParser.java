package io.slack.blockchain.interactive.components.dialogs.parsers;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@Component
public class TransactionSubmissionDialogParser {
	private final Gson gson;

	public TransactionSubmissionDialogParser() {
		gson = new Gson();
	}

	public TransactionDialogSubmission parseSubmittedData(final String payload) {
		final JsonObject payloadAsJson = new JsonParser().parse(payload).getAsJsonObject();
		final TransactionDialogSubmission transactionDialogSubmission = gson
				.fromJson(payloadAsJson.get("submission").getAsJsonObject(), TransactionDialogSubmission.class);
		transactionDialogSubmission.setResponseUrl(payloadAsJson.get("response_url").getAsString());
		return transactionDialogSubmission;
	}
}
