package io.slack.blockchain.domain.dialog.contents;

import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationDialogContent extends DialogContent {
	private final ConfigurationDialogSubmission configurationDialogSubmission;

	public ConfigurationDialogContent(DialogIdentityPayload dialogIdentityPayload,
			ConfigurationDialogSubmission configurationDialogSubmission) {
		super(dialogIdentityPayload);
		this.configurationDialogSubmission = configurationDialogSubmission;
	}

}
