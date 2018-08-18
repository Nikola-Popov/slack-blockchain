package io.transaction.broker.coinbase.security.oauth.exceptions;

public class InvalidResponseAuthorizationStateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidResponseAuthorizationStateException(String message) {
		super(message);
	}
}
