package io.slack.blockchain.services;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;
import io.slack.blockchain.services.ProcessorService;
import io.slack.blockchain.services.dialogs.exceptions.MissingDialogSubmissionException;

@Service
public class ProcessorInitiatorService {
	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	@Autowired
	private ProcessorService processorService;

	public <T> void initiateProcessing(final String payload) {
		final String callbackId = dialogPayloadParser.parseCallbackId(payload);
		if (callbackId.equals(TRANSACTION_DIALOG_CALLBACK_ID)) {
			processorService.process(payload, TransactionDialogSubmission.class);
		} else if (callbackId.equals(CONFIGURATION_DIALOG_CALLBACK_ID)) {
			processorService.process(payload, ConfigurationDialogSubmission.class);
		} else {
			throw new MissingDialogSubmissionException("Invalid callback_id received!");
		}
	}
}
