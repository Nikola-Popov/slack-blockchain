package io.slack.blockchain.coinbase.broker.exceptions;

public class InvalidResponseAuthorizationStateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidResponseAuthorizationStateException(String message) {
		super(message);
	}
}
