package io.slack.blockchain.processors;

import static io.slack.blockchain.domain.attachments.Status.GOOD;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;

@Component
public class SubmittedTransactionProcessor {
	private static final String SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE = "All good to go! You have sucessfuly created a transaction.";

	@Autowired
	private TransactionSubmissionDialogParser transactionSubmissionDialogParser;

	@Autowired
	private AttachmentResponseFactory attachmentResponseFactory;

	public ResponseEntity<AttachmentResponse> processSubmissionDialogData(final String payload) {
		final TransactionDialogSubmission transactionDialogSubmission = transactionSubmissionDialogParser
				.parseSubmittedData(payload);
		System.out.println(transactionDialogSubmission);
		return ok(attachmentResponseFactory.createAttachmentResponse(
				Attachment.builder().text(SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE).status(GOOD).build()));
	}
}
