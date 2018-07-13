package io.slack.blockchain.interactive.components.dialogs.parsers;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import io.slack.blockchain.domain.TransactionDialogSubmission;

@Component
public class TransactionSubmissionDialogParser {
	private final Gson gson;

	public TransactionSubmissionDialogParser() {
		gson = new Gson();
	}

	public TransactionDialogSubmission parseSubmittedData(final String payload) {
		return gson.fromJson(new JsonParser().parse(payload).getAsJsonObject().get("submission").getAsJsonObject(),
				TransactionDialogSubmission.class);
	}
}
