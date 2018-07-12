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
import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;
import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

import io.slack.blockchain.exceptions.DialogOpenException;
import io.slack.blockchain.factories.SlackFactory;
import io.slack.blockchain.interactive.components.dialogs.SlackTransactionsDialogFactory;
import io.slack.blockchain.utils.converters.UserConverter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionDialogService {
	private static final String OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE = "There was an error while trying to open a transaction dialog.";

	@Value("${slack.oauth.token}")
	private String token;

	@Autowired
	private SlackFactory slackFactory;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private SlackTransactionsDialogFactory slackTransactionDialogFactory;

	public void openTransactionDialog(final String triggerId) {
		final Slack slack = slackFactory.createSlack();
		try {
			final List<User> users = slack.methods().usersList(UsersListRequest.builder().token(token).build())
					.getMembers();
			final List<DialogOption> usersDialogOptions = userConverter.convert(users);
			final Dialog dialog = slackTransactionDialogFactory.createTransactionsDialog(usersDialogOptions);
			DialogOpenResponse dialogOpenResponse = slack.methods()
					.dialogOpen(DialogOpenRequest.builder().token(token).triggerId(triggerId).dialog(dialog).build());
			if (!dialogOpenResponse.isOk()) {
				log.error(OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE);
				throw new DialogOpenException(OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE);
			}
		} catch (IOException | SlackApiException exception) {
			log.error(OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE, exception);
			throw new DialogOpenException(OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE, exception);
		}
	}

}
