package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.services.ProcessorInitiatorService;
import io.slack.blockchain.services.dialogs.DialogServiceFactory;
import io.slack.blockchain.services.dialogs.EmailConfigurationDialogService;
import io.slack.blockchain.services.dialogs.TransactionDialogService;

@RestController
public class SlackRestController {
	@Autowired
	private ProcessorInitiatorService processorInitiatorService;

	@Autowired
	private DialogServiceFactory dialogServiceFactory;

	@PostMapping(path = "/configure", produces = APPLICATION_JSON_UTF8_VALUE)
	public void configure(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceFactory.createDialogService(triggerId, EmailConfigurationDialogService.class);
	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public void makeTransaction(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceFactory.createDialogService(triggerId, TransactionDialogService.class);
	}

	@PostMapping(path = "/dialog/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitConfigurations(@RequestParam("payload") final String payload) {
		processorInitiatorService.initiateProcessing(payload);
	}

}
