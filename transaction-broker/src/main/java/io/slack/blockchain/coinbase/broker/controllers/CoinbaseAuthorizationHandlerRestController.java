package io.slack.blockchain.coinbase.broker.controllers;

import static io.slack.blockchain.coinbase.broker.constants.CoinbaseAuthorizationConstants.GRANTED_AUTHORIZATION_URI;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.CODE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.STATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.broker.services.AuthorizationService;

@RestController
public class CoinbaseAuthorizationHandlerRestController {
	@Autowired
	private AuthorizationService authorizationService;

	@GetMapping(path = GRANTED_AUTHORIZATION_URI)
	public void authorize(@RequestParam(CODE) final String code, @RequestParam(STATE) final String state)
			throws AuthorizationException {
		// TODO: verify state
		authorizationService.acquireAccessTokenConsumingCode(code);
	}
}
