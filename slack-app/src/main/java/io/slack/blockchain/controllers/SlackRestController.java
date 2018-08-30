package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.services.ProcessorService;
import io.slack.blockchain.services.dialogs.DialogServiceFactory;
import io.slack.blockchain.services.dialogs.EmailConfigurationDialogService;
import io.slack.blockchain.services.dialogs.TransactionDialogService;

@RestController
public class SlackRestController {
	@Autowired
	private ProcessorService processorService;

	@Autowired
	private DialogServiceFactory dialogServiceFactory;

	@PostMapping(path = "/configure", produces = APPLICATION_JSON_UTF8_VALUE)
	public void configure(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceFactory.createDialogService(triggerId, EmailConfigurationDialogService.class);
	}

	@PostMapping(path = "/configure/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitConfigurations(@RequestParam("payload") final String payload) {
		processorService.process(payload, ConfigurationDialogSubmission.class);
	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public void makeTransaction(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceFactory.createDialogService(triggerId, TransactionDialogService.class);
	}

	@PostMapping(path = "/transaction/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitTransaction(@RequestParam("payload") final String payload) {
		processorService.process(payload, TransactionDialogSubmission.class);
	}
}
