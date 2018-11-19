package io.slack.blockchain.coinbase.security.oauth.exceptions;

public class CoinbaseClientException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoinbaseClientException(final String message) {
		super(message);
	}

	public CoinbaseClientException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
