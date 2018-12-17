package io.slack.blockchain.utils.converters;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.contents.DialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.persitence.User;

@Component
public class UserConverter {
	public User convert(final DialogContent<ConfigurationDialogSubmission> configurationDialogContent) {
		final DialogIdentityPayload dialogIdentityPayload = configurationDialogContent.getDialogIdentityPayload();

		return new User(dialogIdentityPayload.getUser().getName(), dialogIdentityPayload.getUser().getId(),
				dialogIdentityPayload.getTeam().getId(), configurationDialogContent.getDialogSubmission().getEmail());
	}
}
