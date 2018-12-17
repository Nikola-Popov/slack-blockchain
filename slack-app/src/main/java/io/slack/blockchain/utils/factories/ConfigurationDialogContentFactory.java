package io.slack.blockchain.utils.factories;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;

@Component
public class ConfigurationDialogContentFactory implements DialogContentFactory<ConfigurationDialogSubmission> {
	@Override
	public ConfigurationDialogContent createDialogContent(final DialogIdentityPayload dialogIdentityPayload,
			final ConfigurationDialogSubmission configurationDialogSubmission) {
		return new ConfigurationDialogContent(dialogIdentityPayload, configurationDialogSubmission);
	}

}
