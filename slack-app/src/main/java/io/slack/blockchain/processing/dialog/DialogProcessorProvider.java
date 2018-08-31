package io.slack.blockchain.processing.dialog;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@Component
public class DialogProcessorProvider {
	public <T> DialogProcessor provideBasedOn(final T dialog) {
		if (dialog.getClass().isAssignableFrom(TransactionDialogSubmission.class)) {
			return new TransactionDialogProcessor(TransactionDialogSubmission.class.cast(dialog));
		} else if (dialog.getClass().isAssignableFrom(ConfigurationDialogSubmission.class)) {
			return new ConfigurationDialogProcessor(ConfigurationDialogSubmission.class.cast(dialog));
		} else {
			throw new IllegalArgumentException("Invalid dialog submission specified!");
		}
	}
}
