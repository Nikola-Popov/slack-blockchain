package io.slack.blockchain.services;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.interactive.components.dialogs.client.DialogResponder;
import io.slack.blockchain.processing.dialog.DialogProcessor;
import io.slack.blockchain.processing.dialog.DialogProcessorProvider;
import io.slack.blockchain.services.dialogs.exceptions.DialogResponderException;

@Service
public class ProcessorService {
	@Autowired
	private DialogResponder dialogResponder;

	@Autowired
	private DialogProcessorProvider dialogProcessorProvider;

	public <S> void process(final DialogContent<S> dialogContent) {
		final DialogProcessor dialogProcessor = dialogProcessorProvider.provideBasedOn(dialogContent.getSubmission());
		final ProcessingResult processingResult = dialogProcessor.process();
		try {
			dialogResponder.respond(dialogContent.getDialogIdentityPayload().getResponseUrl(),
					processingResult.getStatus(), processingResult.getMessage());
		} catch (URISyntaxException e) {
			throw new DialogResponderException("Failed to respond to Slack after dialog processing!", e);
		}
	}
}
