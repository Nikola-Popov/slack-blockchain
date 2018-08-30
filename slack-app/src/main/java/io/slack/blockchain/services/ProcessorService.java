package io.slack.blockchain.services;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.interactive.components.dialogs.client.DialogResponder;
import io.slack.blockchain.interactive.components.dialogs.parsers.GenericSubmissionDialogParser;
import io.slack.blockchain.processing.DialogProcessor;
import io.slack.blockchain.processing.DialogProcessorProvider;

@Service
public class ProcessorService {
	@Autowired
	private DialogResponder dialogResponder;

	@Autowired
	private GenericSubmissionDialogParser genericSubmissionDialogParser;

	@Autowired
	private DialogProcessorProvider dialogProcessorProvider;

	public <T> void process(final String payload, final Class<T> classOfT) throws URISyntaxException {
		final T parsedClassOfT = genericSubmissionDialogParser.parse(payload, classOfT);
		final DialogProcessor dialogProcessor = dialogProcessorProvider.provideBasedOn(parsedClassOfT);
		final ProcessingResult processingResult = dialogProcessor.process();
		dialogResponder.respond(genericSubmissionDialogParser.parseResponseUrl(payload), processingResult.getStatus(),
				processingResult.getMessage());
	}
}
