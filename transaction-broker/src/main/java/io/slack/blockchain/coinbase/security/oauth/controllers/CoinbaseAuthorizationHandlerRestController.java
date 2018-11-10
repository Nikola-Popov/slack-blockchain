package io.slack.blockchain.coinbase.security.oauth.controllers;

import static io.slack.blockchain.coinbase.security.oauth.constants.CoinbaseAuthorizationConstants.GRANTED_AUTHORIZATION_URI;
import static io.slack.blockchain.coinbase.security.oauth.constants.QueryParamsConstants.CODE;
import static io.slack.blockchain.coinbase.security.oauth.constants.QueryParamsConstants.STATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.slack.blockchain.coinbase.security.oauth.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.security.oauth.services.AuthorizationService;

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
