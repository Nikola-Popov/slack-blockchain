package io.slack.blockchain.utils.converters;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.DialogContent;

@Component
public class DialogContentUtil {
	public <S, D> boolean isDialogContentSubmissionAssignableFrom(final DialogContent<D> dialogContent, final Class<S> assignableSubmissionClass) {
		return dialogContent.getSubmission().getClass().isAssignableFrom(assignableSubmissionClass);
	}
}
