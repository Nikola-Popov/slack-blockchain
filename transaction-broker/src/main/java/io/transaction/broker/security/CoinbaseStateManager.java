package io.transaction.broker.security;

import static java.util.UUID.randomUUID;

import org.springframework.stereotype.Component;

@Component
public class CoinbaseStateManager {
	private String state = "";

	public void verify(final String responseState) {
		if (!state.equals(responseState)) {
			throw new RuntimeException("Invalid response state! Possible cross-site scripting detected!");
		}
	}

	public String generateRandomState() {
		state = randomUUID().toString();
		return state;
	}
}
