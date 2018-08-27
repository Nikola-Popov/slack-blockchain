package io.slack.blockchain.coinbase.security.oauth.controllers;

import static io.slack.blockchain.coinbase.security.oauth.constants.CoinbaseAuthorizationConstants.GRANTED_AUTHORIZATION_URI;
import static io.slack.blockchain.coinbase.security.oauth.constants.QueryParamsConstants.CODE;
import static io.slack.blockchain.coinbase.security.oauth.constants.QueryParamsConstants.STATE;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.coinbase.security.oauth.domain.OAuthResponse;
import io.slack.blockchain.coinbase.security.oauth.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import io.slack.blockchain.commons.http.RequestEntityFactory;

@RestController
public class CoinbaseAuthorizationRestController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	@GetMapping(path = GRANTED_AUTHORIZATION_URI)
	public void acquireAccessToken(@RequestParam(CODE) final String code, @RequestParam(STATE) final String state)
			throws RestClientException, URISyntaxException {
		// TODO: verify state
		final String coinbaseAcessTokenEndpoint = coinbaseAuthorizationEndpointBuilderUtil
				.buildAccessTokenEndpoint(code, "redirectUrl");

		final ResponseEntity<OAuthResponse> responseEntity = restTemplate.exchange(
				requestEntityFactory.createPostRequestEntity(coinbaseAcessTokenEndpoint), OAuthResponse.class);
	}
}
