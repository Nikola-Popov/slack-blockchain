package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.seratch.jslack.api.methods.SlackApiException;

import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.services.TransactionProcessorService;
import io.slack.blockchain.services.UserService;
import io.slack.blockchain.services.dialogs.DialogService;

@RestController
public class SlackRestController {
	@Autowired
	private DialogService transactionDialogService;

	@Autowired
	private DialogService emailConfigurationDialogService;

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionProcessorService transactionProcessorService;

	// @Autowired
	// private CoinbaseAuthorizationInitiator coinbaseAuthorizationInitiator;

	@PostMapping(path = "/configure", produces = APPLICATION_JSON_UTF8_VALUE)
	public void configure(@RequestParam("trigger_id") final String triggerId,
			@RequestParam("user_id") final String userId, @RequestParam("team_id") final String teamId,
			@RequestParam("text") final String email) throws IOException, SlackApiException {
		// return
		// ok(userService.process(SlackUser.builder().userId(userId).teamId(teamId).email(email).build()));
		emailConfigurationDialogService.openDialog(triggerId);
	}

	@PostMapping(path = "/configure/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitConfigurations(@RequestParam("payload") final String payload) {

	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Attachment> makeTransaction(@RequestParam("trigger_id") final String triggerId) {
		transactionDialogService.openDialog(triggerId);
		return ok().build();
	}

	@PostMapping(path = "/transaction/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitTransaction(@RequestParam("payload") final String payload) throws URISyntaxException {
		transactionProcessorService.process(payload);
	}
}
