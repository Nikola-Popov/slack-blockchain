package io.slack.blockchain.services;

import static io.slack.blockchain.commons.utils.AttachmentResponseJsonBuilder.buildJsonResponse;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;
import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.google.gson.JsonSyntaxException;

import io.slack.blockchain.commons.factories.SlackFactory;
import io.slack.blockchain.commons.utils.converters.UserConverter;
import io.slack.blockchain.domain.attachments.ColorStatus;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.DialogResponseValidator;
import io.slack.blockchain.interactive.components.dialogs.SlackTransactionsDialogFactory;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;

@Service
public class TransactionDialogService {
	private static final String DIALOG_OPEN_ERROR_MESSAGE = "An error occured while trying to open the transaction dialog window";
	private static final String INVALID_AMOUNT_RESPONSE_MESSAGE = "Invalid amount type specified. Please, note that only numbers are allowed!";
	private static final String SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE = "All good to go! You have sucessfuly commited a transaction.";

	@Value("${slack.oauth.token}")
	private String token;

	@Autowired
	private SlackFactory slackFactory;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private SlackTransactionsDialogFactory slackTransactionDialogFactory;

	@Autowired
	private TransactionSubmissionDialogParser transactionSubmissionDialogParser;

	@Autowired
	private DialogResponseValidator dialogResponseValidator;

	public ResponseEntity<String> openTransactionDialog(final String triggerId) {
		try {
			final Slack slack = slackFactory.createSlack();
			final List<User> users = slack.methods().usersList(UsersListRequest.builder().token(token).build())
					.getMembers();
			final List<DialogOption> usersDialogOptions = userConverter.convert(users);
			final Dialog dialog = slackTransactionDialogFactory.createTransactionsDialog(usersDialogOptions);
			DialogOpenResponse dialogOpenResponse = slack.methods()
					.dialogOpen(DialogOpenRequest.builder().token(token).triggerId(triggerId).dialog(dialog).build());

			dialogResponseValidator.checkValidity(dialogOpenResponse);
		} catch (IOException | SlackApiException | DialogOpenException exception) {
			return status(INTERNAL_SERVER_ERROR).body(buildJsonResponse(DIALOG_OPEN_ERROR_MESSAGE, ColorStatus.DANGER));
		}
		return ok().build();
	}

	public ResponseEntity<String> processSubmissionDialogData(final String payload) {
		try {
			final TransactionDialogSubmission transactionDialogSubmission = transactionSubmissionDialogParser
					.parseSubmittedData(payload);
			System.out.println(transactionDialogSubmission);
		} catch (JsonSyntaxException numberFormatException) {
			return badRequest().body(INVALID_AMOUNT_RESPONSE_MESSAGE);
		}
		return ok(SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE);
	}

}
