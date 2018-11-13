package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.services.ProcessorInitiatorService;
import io.slack.blockchain.services.dialogs.DialogServiceBeanLoader;
import io.slack.blockchain.services.dialogs.EmailConfigurationDialogService;
import io.slack.blockchain.services.dialogs.TransactionDialogService;

@RestController("/slack")
public class SlackRestController {
	@Autowired
	private ProcessorInitiatorService processorInitiatorService;

	@Autowired
	private DialogServiceBeanLoader dialogServiceBeanLoader;

	@PostMapping(path = "/configure", produces = APPLICATION_JSON_UTF8_VALUE)
	public void openConfigurationDialog(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceBeanLoader.getDialogService(EmailConfigurationDialogService.class).openDialog(triggerId);
	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public void openTransactionDialog(@RequestParam("trigger_id") final String triggerId) {
		dialogServiceBeanLoader.getDialogService(TransactionDialogService.class).openDialog(triggerId);
	}

	@PostMapping(path = "/dialog/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void processDialog(@RequestParam("payload") final String payload) {
		processorInitiatorService.initiateProcessing(payload);
	}
}
