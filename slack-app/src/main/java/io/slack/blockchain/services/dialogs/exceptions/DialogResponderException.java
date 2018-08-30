package io.slack.blockchain.services.dialogs.exceptions;

public class DialogResponderException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DialogResponderException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
