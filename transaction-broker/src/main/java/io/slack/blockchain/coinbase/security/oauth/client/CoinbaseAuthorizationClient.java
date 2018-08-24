package io.slack.blockchain.coinbase.security.oauth.client;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.slack.blockchain.coinbase.security.oauth.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import io.slack.blockchain.coinbase.security.oauth.utils.CoinbaseStateManager;

@Component
public class CoinbaseAuthorizationClient {
	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	@Autowired
	private CoinbaseStateManager coinbaseStateManager;

	public ResponseEntity<Void> initiateAuthorization() {
		final String coinbaseAuthorizationEndpoint = coinbaseAuthorizationEndpointBuilderUtil
				.buildAuthorizationEndpoint(coinbaseStateManager.generateRandomState());
		return status(MOVED_PERMANENTLY).header(LOCATION, coinbaseAuthorizationEndpoint).build();
	}
}
