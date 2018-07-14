package io.slack.blockchain.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

import io.slack.blockchain.commons.factories.SlackFactory;
import io.slack.blockchain.commons.utils.converters.UserConverter;
import io.slack.blockchain.interactive.components.dialogs.SlackTransactionsDialogFactory;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;

@Service
public class TransactionDialogService {
	private static final String SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE = "All good to go! You have sucessfuly commited a transaction.";

	@Value("${slack.oauth.token}")
	private String token;

	@Autowired
	private SlackFactory slackFactory;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private SlackTransactionsDialogFactory slackTransactionDialogFactory;

	public void openTransactionDialog(final String triggerId) {
		try {
			final Slack slack = slackFactory.createSlack();
			final List<User> users = slack.methods().usersList(UsersListRequest.builder().token(token).build())
					.getMembers();

			final List<DialogOption> usersDialogOptions = userConverter.convert(users);
			final Dialog dialog = slackTransactionDialogFactory.createTransactionsDialog(usersDialogOptions);

			slack.methods()
					.dialogOpen(DialogOpenRequest.builder().token(token).triggerId(triggerId).dialog(dialog).build());
		} catch (IOException | SlackApiException exception) {
			throw new DialogOpenException(exception);
		}
	}
}
