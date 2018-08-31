package io.slack.blockchain.services;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.interactive.components.dialogs.client.DialogResponder;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;
import io.slack.blockchain.processing.dialog.DialogProcessor;
import io.slack.blockchain.processing.dialog.DialogProcessorProvider;
import io.slack.blockchain.services.dialogs.exceptions.DialogResponderException;

@Service
public class ProcessorService {
	@Autowired
	private DialogResponder dialogResponder;

	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	@Autowired
	private DialogProcessorProvider dialogProcessorProvider;

	public <T> void process(final String payload, final Class<T> classOfT) {
		final T parsedClassOfT = dialogPayloadParser.parseSubmission(payload, classOfT);
		final DialogProcessor dialogProcessor = dialogProcessorProvider.provideBasedOn(parsedClassOfT);
		final ProcessingResult processingResult = dialogProcessor.process();
		try {
			dialogResponder.respond(dialogPayloadParser.parseResponseUrl(payload), processingResult.getStatus(),
					processingResult.getMessage());
		} catch (URISyntaxException e) {
			throw new DialogResponderException("Failed to respond to Slack after dialog processing!", e);
		}
	}
}
