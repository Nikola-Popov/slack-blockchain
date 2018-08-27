package io.slack.blockchain.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.coinbase.security.oauth.client.CoinbaseAuthorizationInitiator;
import io.slack.blockchain.domain.SlackUser;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.services.TransactionDialogService;
import io.slack.blockchain.services.UserService;

@RestController
public class SlackRestController {
	@Autowired
	private TransactionDialogService transactionDialogService;

	@Autowired
	private UserService userService;

	@Autowired
	private CoinbaseAuthorizationInitiator coinbaseAuthorizationInitiator;

	@PostMapping(path = "/configure/email", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<AttachmentResponse> configureAddress(@RequestParam("trigger_id") final String triggerId,
			@RequestParam("user_id") final String userId, @RequestParam("team_id") final String teamId,
			@RequestParam("text") final String email) {
		return ok(userService.process(SlackUser.builder().userId(userId).teamId(teamId).email(email).build()));
	}

	@PostMapping(path = "/transaction", produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Attachment> makeTransaction(@RequestParam("trigger_id") final String triggerId) {
		transactionDialogService.openTransactionDialog(triggerId);
		return ok().build();
	}

	@PostMapping(path = "/transaction/submit", produces = APPLICATION_JSON_UTF8_VALUE)
	public void submitTransaction(@RequestParam("payload") final String payload) throws URISyntaxException {
		coinbaseAuthorizationInitiator.initiateAuthorization();
		transactionDialogService.processTransaction(payload);
	}
}
