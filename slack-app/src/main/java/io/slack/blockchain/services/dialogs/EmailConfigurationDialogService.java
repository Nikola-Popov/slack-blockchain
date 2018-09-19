package io.slack.blockchain.services.dialogs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.model.dialog.Dialog;

import io.slack.blockchain.commons.configurations.slack.SlackConfigurationProperties;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.interactive.components.dialogs.factories.EmailConfigurationDialogFactory;
import lombok.Setter;

@Service
@Setter
public class EmailConfigurationDialogService implements DialogService {
	@Autowired
	private Slack slack;

	@Autowired
	private EmailConfigurationDialogFactory emailConfigurationDialogFactory;

	@Autowired
	private SlackConfigurationProperties slackConfigurationProperties;

	@Autowired
	private DialogOpenResponseHandler dialogOpenResponseHandler;

	@Override
	public void openDialog(String triggerId) {
		try {
			final Dialog dialog = emailConfigurationDialogFactory.createEmailConfigurationDialog();
			dialogOpenResponseHandler.handleDialogOpenResponse(slack.methods().dialogOpen(DialogOpenRequest.builder()
					.token(slackConfigurationProperties.getOauthToken()).triggerId(triggerId).dialog(dialog).build()));
		} catch (IOException | SlackApiException exception) {
			throw new DialogOpenException(exception);
		}
	}

}
