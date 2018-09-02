package io.slack.blockchain.utils.converters.dialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogContent;

@Component
public class ConfigurationDialogContentConverter implements DialogContentConverter<ConfigurationDialogSubmission> {
	@Autowired
	private DialogSubmissionConverter dialogSubmissionConverter;

	@Override
	public <S> DialogContent<ConfigurationDialogSubmission> convert(final DialogContent<S> dialogContent) {
		return new DialogContent<ConfigurationDialogSubmission>(dialogContent.getDialogIdentityPayload(),
				dialogSubmissionConverter.convertToConfigurationDialogSubmission(dialogContent.getSubmission()));
	}
}
