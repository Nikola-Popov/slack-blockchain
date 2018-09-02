package io.slack.blockchain.utils.converters.dialog;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@Component
public class DialogSubmissionConverter {
	public <S> TransactionDialogSubmission convertToTransactionDialogSubmssion(final S submission) {
		return TransactionDialogSubmission.class.cast(submission);
	}

	public <S> ConfigurationDialogSubmission convertToConfigurationDialogSubmission(final S submission) {
		return ConfigurationDialogSubmission.class.cast(submission);
	}

}
