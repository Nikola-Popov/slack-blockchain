package io.slack.blockchain.services.dialogs.exceptions;

public class MissingDialogSubmissionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MissingDialogSubmissionException(final String message) {
		super(message);
	}
}
