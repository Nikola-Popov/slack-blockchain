package io.slack.blockchain.services.processing.exceptions;

public class MissingDialogSubmissionException extends ProcessingException {
	private static final long serialVersionUID = 1L;

	public MissingDialogSubmissionException(final String message) {
		super(message);
	}
}
