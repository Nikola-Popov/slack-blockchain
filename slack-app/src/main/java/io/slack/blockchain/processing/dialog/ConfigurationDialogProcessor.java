package io.slack.blockchain.processing.dialog;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.processing.ProcessingResult;

public class ConfigurationDialogProcessor implements DialogProcessor {
	private ConfigurationDialogSubmission configurationDialogSubmission;

	public ConfigurationDialogProcessor(final ConfigurationDialogSubmission configurationDialogSubmission) {
		this.configurationDialogSubmission = configurationDialogSubmission;
	}

	@Override
	public ProcessingResult process() {
		// TODO Auto-generated method stub
		return null;
	}

}
