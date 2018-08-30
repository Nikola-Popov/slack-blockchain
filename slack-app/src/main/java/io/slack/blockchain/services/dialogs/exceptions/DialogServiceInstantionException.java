package io.slack.blockchain.services.dialogs.exceptions;

public class DialogServiceInstantionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DialogServiceInstantionException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
