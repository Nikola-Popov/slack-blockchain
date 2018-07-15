package io.slack.blockchain.interactive.components.dialogs.parsers;

import static io.slack.blockchain.interactive.components.dialogs.constants.SubmittedDialogConstants.RESPONSE_URL;
import static io.slack.blockchain.interactive.components.dialogs.constants.SubmittedDialogConstants.SUBMISSION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import io.slack.blockchain.commons.GsonJsonProvider;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@Component
public class TransactionSubmissionDialogParser {

	@Autowired
	private GsonJsonProvider gsonJsonProvider;

	public TransactionDialogSubmission parseSubmittedData(final String payload) {
		final JsonObject payloadAsJson = gsonJsonProvider.parse(payload).getAsJsonObject();
		final TransactionDialogSubmission transactionDialogSubmission = gsonJsonProvider
				.fromJson(payloadAsJson.get(SUBMISSION).getAsJsonObject(), TransactionDialogSubmission.class);
		transactionDialogSubmission.setResponseUrl(payloadAsJson.get(RESPONSE_URL).getAsString());
		return transactionDialogSubmission;
	}
}
