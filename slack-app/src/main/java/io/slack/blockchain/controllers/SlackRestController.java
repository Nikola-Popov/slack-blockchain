package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.services.ProcessorService;
import io.slack.blockchain.services.dialogs.DialogService;

@RestController("/slack")
public class SlackRestController {
	@Autowired
	private ProcessorService processorService;

	@Autowired
	private DialogService transactionDialogService;

	@Autowired
	private DialogService emailConfigurationDialogService;

	@PostMapping(path = "/configure", produces = APPLICATION_JSON_UTF8_VALUE)
	public void initiateConfiguration(@RequestParam("trigger_id") final String triggerId) {
		emailConfigurationDialogService.openDialog(triggerId);

	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public void initiateTransaction(@RequestParam("trigger_id") final String triggerId) {
		transactionDialogService.openDialog(triggerId);
	}

	@PostMapping(path = "/dialog/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void processDialog(@RequestParam("payload") final String payload) {
		final ProcessingResult processingResult = processorService.process(payload);
	}
}
