package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.services.ProcessorService;
import io.slack.blockchain.services.dialogs.DialogServiceFactory;
import io.slack.blockchain.services.dialogs.EmailConfigurationDialogService;
import io.slack.blockchain.services.dialogs.TransactionDialogService;

@RestController
public class SlackRestController {
	@Autowired
	private ProcessorService transactionProcessorService;

	@Autowired
	private DialogServiceFactory dialogServiceFactory;
	// @Autowired
	// private CoinbaseAuthorizationInitiator coinbaseAuthorizationInitiator;

	@PostMapping(path = "/configure", produces = APPLICATION_JSON_UTF8_VALUE)
	public void configure(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceFactory.createDialogService(triggerId, EmailConfigurationDialogService.class);
	}

	@PostMapping(path = "/configure/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitConfigurations(@RequestParam("payload") final String payload) {

	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public void makeTransaction(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceFactory.createDialogService(triggerId, TransactionDialogService.class);
	}

	@PostMapping(path = "/transaction/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitTransaction(@RequestParam("payload") final String payload) throws URISyntaxException {
		private static final String SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE = "All good to go! You have sucessfuly created a transaction.";

		transactionProcessorService.process(payload);
	}
}
