package io.slack.blockchain.processing.dialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.utils.DialogContentUtil;
import io.slack.blockchain.utils.converters.dialog.DialogContentConverter;

@Component
public class DialogProcessorProvider {
	@Autowired
	private DialogContentConverter<TransactionDialogSubmission> transactionDialogContentConverter;

	@Autowired
	private DialogContentConverter<ConfigurationDialogSubmission> configurationDialogContentConverter;

	@Autowired
	private DialogContentUtil dialogContentUtil;

	public <S> DialogProcessor provide(final DialogContent<S> dialogContent) {
		if (dialogContentUtil.isDialogContentSubmissionAssignableFrom(dialogContent,
				TransactionDialogSubmission.class)) {
			return new TransactionDialogProcessor(transactionDialogContentConverter.convert(dialogContent));
		} else if (dialogContentUtil.isDialogContentSubmissionAssignableFrom(dialogContent,
				ConfigurationDialogSubmission.class)) {
			return new ConfigurationDialogProcessor(configurationDialogContentConverter.convert(dialogContent));
		} else {
			throw new IllegalArgumentException("Invalid dialog submission specified!");
		}
	}
}
