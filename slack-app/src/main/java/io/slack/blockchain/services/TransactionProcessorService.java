package io.slack.blockchain.services;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.client.TransactionDialogResponder;
import io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionProcessorService {
	@Autowired
	private TransactionSubmissionDialogParser transactionSubmissionDialogParser;

	@Autowired
	private TransactionDialogResponder transactionDialogResponder;

	public void process(final String payload) throws URISyntaxException {
		final TransactionDialogSubmission transactionDialogSubmission = transactionSubmissionDialogParser
				.parse(payload);
		log.info(transactionDialogSubmission.toString());

		transactionDialogResponder.respond(transactionDialogSubmission.getResponseUrl());
	}
}
