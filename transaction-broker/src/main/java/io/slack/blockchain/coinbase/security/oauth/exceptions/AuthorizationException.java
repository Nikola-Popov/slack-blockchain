package io.slack.blockchain.coinbase.security.oauth.exceptions;

public class AuthorizationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthorizationException(final String message) {
		super(message);
	}

	public AuthorizationException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
