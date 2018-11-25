package io.slack.blockchain.services;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;
import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;
import io.slack.blockchain.processing.dialog.DialogProcessor;
import io.slack.blockchain.services.dialogs.exceptions.MissingDialogSubmissionException;

@Service
public class ProcessorService {
	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	@Autowired
	private DialogProcessor<ConfigurationDialogContent> configurationDialogProcessor;

	@Autowired
	private DialogProcessor<TransactionDialogContent> transactionDialogProcessor;

	public ProcessingResult process(final String payload) {
		DialogIdentityPayload dialogIdentityPayload = dialogPayloadParser.parseIdentity(payload);
		final String callbackId = dialogIdentityPayload.getCallbackId();

		if (callbackId.equals(TRANSACTION_DIALOG_CALLBACK_ID)) {
			TransactionDialogSubmission transactionDialogSubmission = dialogPayloadParser.parseSubmission(payload,
					TransactionDialogSubmission.class);

			return transactionDialogProcessor
					.process(new TransactionDialogContent(dialogIdentityPayload, transactionDialogSubmission));
		} else if (callbackId.equals(CONFIGURATION_DIALOG_CALLBACK_ID)) {
			ConfigurationDialogSubmission configurationDialogSubmission = dialogPayloadParser.parseSubmission(payload,
					ConfigurationDialogSubmission.class);

			return configurationDialogProcessor
					.process(new ConfigurationDialogContent(dialogIdentityPayload, configurationDialogSubmission));
		} else {
			throw new MissingDialogSubmissionException("Invalid callback_id received!");
		}
	}
}
