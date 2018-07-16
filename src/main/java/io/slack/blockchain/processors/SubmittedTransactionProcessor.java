package io.slack.blockchain.processors;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.client.TransactionSubmittedDialogResponder;
import io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;

@Component
public class SubmittedTransactionProcessor {
	@Autowired
	private TransactionSubmissionDialogParser transactionSubmissionDialogParser;

	@Autowired
	private TransactionSubmittedDialogResponder transactionSubmittedDialogResponder;

	public void processSubmissionDialogData(final String payload) throws URISyntaxException {
		final TransactionDialogSubmission transactionDialogSubmission = transactionSubmissionDialogParser
				.parseSubmittedData(payload);
		transactionSubmittedDialogResponder.respond(transactionDialogSubmission.getResponseUrl());
	}
}
