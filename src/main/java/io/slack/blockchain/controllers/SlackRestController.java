package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.domain.SlackUser;
import io.slack.blockchain.services.TransactionDialogService;
import io.slack.blockchain.services.UserService;

@RestController
public class SlackRestController {
	private static final String GOOD = "good";
	private static final String SUCESSFUL_SUBMMITED_TRANSACTION_MESSAGE = "All good to go! Your transaction was submitted succesfuly.";
	private static final String ADDRESS_CONFIGURED_RESPONSE_MESSAGE = "Bitcoin address configured successfuly!\nYou are now able to make transactions.";
	private static final String WARNING = "warning";

	@Autowired
	private TransactionDialogService transactionDialogService;

	@Autowired
	private UserService userService;

	@PostMapping(path = "/configure/address", produces = APPLICATION_JSON_UTF8_VALUE)
	public void configureAddress(@RequestParam("trigger_id") final String triggerId,
			@RequestParam("user_id") final String userId, @RequestParam("team_id") final String teamId,
			@RequestParam("text") final String cryptoCurrencyAddress) {
		userService.process(
				SlackUser.builder().userId(userId).teamId(teamId).cryptoCurrencyAddress(cryptoCurrencyAddress).build());
		// return ok(buildJsonResponse(
		// AttachmentResponse.builder().text(ADDRESS_CONFIGURED_RESPONSE_MESSAGE).color(WARNING).build()));
	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> makeTransaction(@RequestParam("trigger_id") final String triggerId) {
		return transactionDialogService.openTransactionDialog(triggerId);
	}

	@PostMapping(path = "/transaction/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> submitTransaction(@RequestParam("payload") final String payload) {
		System.out.println("hello world" + payload);
		return transactionDialogService.processSubmissionDialogData(payload);
	}
}
