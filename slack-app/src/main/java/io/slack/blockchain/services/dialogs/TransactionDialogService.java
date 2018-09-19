package io.slack.blockchain.services.dialogs;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

import io.slack.blockchain.commons.configurations.slack.SlackConfigurationProperties;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.interactive.components.dialogs.factories.TransactionsDialogFactory;
import io.slack.blockchain.utils.converters.UserConverter;
import lombok.Setter;

@Service
@Setter
public class TransactionDialogService implements DialogService {

	@Autowired
	private Slack slack;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private TransactionsDialogFactory slackTransactionDialogFactory;

	@Autowired
	private SlackConfigurationProperties slackConfigurationProperties;

	@Autowired
	private DialogOpenResponseHandler dialogOpenResponseHandler;

	@Override
	public void openDialog(final String triggerId) {
		try {
			final List<User> users = slack.methods()
					.usersList(UsersListRequest.builder().token(slackConfigurationProperties.getOauthToken()).build())
					.getMembers();

			final List<DialogOption> usersDialogOptions = userConverter.convert(users);
			final Dialog dialog = slackTransactionDialogFactory.createTransactionsDialog(usersDialogOptions);

			dialogOpenResponseHandler.handleDialogOpenResponse(slack.methods().dialogOpen(DialogOpenRequest.builder()
					.token(slackConfigurationProperties.getOauthToken()).triggerId(triggerId).dialog(dialog).build()));
		} catch (IOException | SlackApiException exception) {
			throw new DialogOpenException(exception);
		}
	}
}
