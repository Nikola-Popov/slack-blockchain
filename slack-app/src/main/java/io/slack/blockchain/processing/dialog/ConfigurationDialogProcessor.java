package io.slack.blockchain.processing.dialog;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.processing.ProcessingResult;

public class ConfigurationDialogProcessor implements DialogProcessor {
	private DialogContent<ConfigurationDialogSubmission> dialogContent;

	public ConfigurationDialogProcessor(DialogContent<ConfigurationDialogSubmission> dialogContent) {
		this.dialogContent = dialogContent;
	}

	@Override
	public ProcessingResult process() {
		return null;
	}

}
