package io.slack.blockchain.exceptions;

public class DialogOpenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DialogOpenException(String message) {
		super(message);
	}

	public DialogOpenException(String message, Throwable cause) {
		super(message, cause);
	}
}
