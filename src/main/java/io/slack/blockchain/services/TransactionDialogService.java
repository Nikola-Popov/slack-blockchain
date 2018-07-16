package io.slack.blockchain.services;

import java.io.IOException;
import java.net.URISyntaxException;
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
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.interactive.components.dialogs.factories.SlackTransactionsDialogFactory;
import io.slack.blockchain.processors.SubmittedTransactionProcessor;
import io.slack.blockchain.utils.converters.UserConverter;

@Service
public class TransactionDialogService {
	@Value("${slack.oauth.token}")
	private String slackOauthToken;

	@Autowired
	private SlackFactory slackFactory;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private SlackTransactionsDialogFactory slackTransactionDialogFactory;

	@Autowired
	private SubmittedTransactionProcessor submittedTransactionProcessor;

	public void openTransactionDialog(final String triggerId) {
		try {
			final Slack slack = slackFactory.createSlack();
			final List<User> users = slack.methods()
					.usersList(UsersListRequest.builder().token(slackOauthToken).build()).getMembers();

			final List<DialogOption> usersDialogOptions = userConverter.convert(users);
			final Dialog dialog = slackTransactionDialogFactory.createTransactionsDialog(usersDialogOptions);

			slack.methods().dialogOpen(
					DialogOpenRequest.builder().token(slackOauthToken).triggerId(triggerId).dialog(dialog).build());
		} catch (IOException | SlackApiException exception) {
			throw new DialogOpenException(exception);
		}
	}

	public void processTransaction(final String payload) throws URISyntaxException {
		submittedTransactionProcessor.processSubmissionDialogData(payload);
	}
}
