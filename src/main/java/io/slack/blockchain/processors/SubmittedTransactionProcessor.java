package io.slack.blockchain.processors;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;

public class SubmittedTransactionProcessor {
	@Autowired
	private TransactionSubmissionDialogParser transactionSubmissionDialogParser;

	public ResponseEntity<String> processSubmissionDialogData(final String payload) {
		final TransactionDialogSubmission transactionDialogSubmission = transactionSubmissionDialogParser
				.parseSubmittedData(payload);
		System.out.println(transactionDialogSubmission);
		return ok(SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE);
	}
}
