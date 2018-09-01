package io.slack.blockchain.processing.dialog;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@Component
public class DialogProcessorProvider {
	public <S> DialogProcessor provideBasedOn(final S submission) {
		if (submission.getClass().isAssignableFrom(TransactionDialogSubmission.class)) {
			return new TransactionDialogProcessor(TransactionDialogSubmission.class.cast(submission));
		} else if (submission.getClass().isAssignableFrom(ConfigurationDialogSubmission.class)) {
			return new ConfigurationDialogProcessor(ConfigurationDialogSubmission.class.cast(submission));
		} else {
			throw new IllegalArgumentException("Invalid dialog submission specified!");
		}
	}
}
