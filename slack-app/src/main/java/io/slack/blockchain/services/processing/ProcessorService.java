package io.slack.blockchain.services.processing;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;
import io.slack.blockchain.processing.dialog.DialogProcessor;
import io.slack.blockchain.services.processing.exceptions.MissingDialogSubmissionException;
import io.slack.blockchain.services.processing.exceptions.ProcessingException;
import io.slack.blockchain.utils.factories.DialogContentFactory;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessorService {
	private static final String FAILED_TO_SEND_RESPONSE_TO_SLACK = "Failed to send response to Slack";
	private static final String INVALID_CALLBACK_ID_RECEIVED = "Invalid callback_id received!";

	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	@Autowired
	private DialogProcessor<ConfigurationDialogContent> configurationDialogProcessor;

	@Autowired
	private DialogProcessor<TransactionDialogContent> transactionDialogProcessor;

	@Autowired
	private DialogContentFactory<ConfigurationDialogSubmission> configurationDialogContentFactory;

	@Autowired
	private DialogContentFactory<TransactionDialogSubmission> transactionDialogContentFactory;

	@Autowired
	private Slack slack;

	public void process(final String payload) throws ProcessingException {
		final DialogIdentityPayload dialogIdentityPayload = dialogPayloadParser.parseIdentity(payload);
		final String callbackId = dialogIdentityPayload.getCallbackId();

		Payload responsePayload = null;
		if (TRANSACTION_DIALOG_CALLBACK_ID.equals(callbackId)) {
			TransactionDialogSubmission transactionDialogSubmission = dialogPayloadParser.parseSubmission(payload,
					TransactionDialogSubmission.class);

			responsePayload = transactionDialogProcessor.process(
					transactionDialogContentFactory.create(dialogIdentityPayload, transactionDialogSubmission));
		} else if (CONFIGURATION_DIALOG_CALLBACK_ID.equals(callbackId)) {
			ConfigurationDialogSubmission configurationDialogSubmission = dialogPayloadParser.parseSubmission(payload,
					ConfigurationDialogSubmission.class);

			responsePayload = configurationDialogProcessor.process(
					configurationDialogContentFactory.create(dialogIdentityPayload, configurationDialogSubmission));
		} else {
			log.error(INVALID_CALLBACK_ID_RECEIVED);
			throw new MissingDialogSubmissionException(INVALID_CALLBACK_ID_RECEIVED);
		}

		sendResponseToSlack(dialogIdentityPayload.getResponseUrl(), responsePayload);
	}

	private void sendResponseToSlack(final String url, Payload responsePayload) throws ProcessingException {
		try {
			slack.send(url, responsePayload);
		} catch (IOException ioException) {
			log.error(FAILED_TO_SEND_RESPONSE_TO_SLACK, ioException);
			throw new ProcessingException(FAILED_TO_SEND_RESPONSE_TO_SLACK, ioException);
		}
	}
}
