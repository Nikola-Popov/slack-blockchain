package io.slack.blockchain.coinbase.security.oauth.utils;

import static java.util.UUID.randomUUID;

import org.springframework.stereotype.Component;

import io.slack.blockchain.coinbase.security.oauth.exceptions.InvalidResponseAuthorizationStateException;

@Component
public class CoinbaseStateManager {
	private String state = "";

	public void verify(final String responseState) {
		if (!state.equals(responseState)) {
			throw new InvalidResponseAuthorizationStateException(
					"Invalid response state! Possible cross-site scripting detected!");
		}
	}

	public String generateRandomState() {
		state = randomUUID().toString();
		return state;
	}
}
