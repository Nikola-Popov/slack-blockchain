package io.slack.blockchain.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.MethodsClient;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;
import com.github.seratch.jslack.api.methods.response.users.UsersListResponse;
import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.TransactionsDialogFactory;
import io.slack.blockchain.interactive.components.dialogs.client.TransactionDialogResponder;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.processors.SubmittedTransactionProcessor;
import io.slack.blockchain.utils.converters.UserConverter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDialogServiceTest {
	private static final String RESPONSE_URL = "responseUrl";
	private static final String SLACK_OAUTH_TOKEN_FIELD_NAME = "slackOauthToken";
	private static final String SLACK_OUATH_TOKEN = "slack-oauth-token";
	private static final String TRIGGER_ID = "triggerId";
	private static final String PAYLOAD = "payload";
	private static final Dialog DIALOG = Dialog.builder().title("dialog-title").build();

	@InjectMocks
	private TransactionDialogService transactionDialogService;

	@MockBean
	private Slack slack;

	@Mock
	private UserConverter userConverterMock;

	@Mock
	private TransactionsDialogFactory slackTransactionDialogFactoryMock;

	@Mock
	private SubmittedTransactionProcessor submittedTransactionProcessorMock;

	@Mock
	private Slack slackMock;

	@Mock
	private MethodsClient methodsClientMock;

	@Mock
	private UsersListResponse usersListResponseMock;

	@Mock
	private List<User> users;

	@Mock
	private List<DialogOption> usersDialogOptions;

	@Mock
	private DialogOpenResponse dialogOpenResponseMock;

	@Mock
	private TransactionDialogResponder transactionDialogResponderMock;

	@Mock
	private TransactionDialogSubmission transactionDialogSubmissionMock;

	@Before
	public void setup() throws Exception {
		when(slackMock.methods()).thenReturn(methodsClientMock);
		when(methodsClientMock.usersList(any(UsersListRequest.class))).thenReturn(usersListResponseMock);
		when(usersListResponseMock.getMembers()).thenReturn(users);

		when(userConverterMock.convert(users)).thenReturn(usersDialogOptions);
		when(slackTransactionDialogFactoryMock.createTransactionsDialog(usersDialogOptions)).thenReturn(DIALOG);

		when(submittedTransactionProcessorMock.process(PAYLOAD)).thenReturn(transactionDialogSubmissionMock);
		when(transactionDialogSubmissionMock.getResponseUrl()).thenReturn(RESPONSE_URL);
	}

	@Test
	public void testOpenTransactionDialogHappyPath() throws Exception {
		setField(transactionDialogService, SLACK_OAUTH_TOKEN_FIELD_NAME, SLACK_OUATH_TOKEN);

		transactionDialogService.openDialog(TRIGGER_ID);

		verify(methodsClientMock).dialogOpen(
				DialogOpenRequest.builder().token(SLACK_OUATH_TOKEN).triggerId(TRIGGER_ID).dialog(DIALOG).build());
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogUsersListThrowsIOException() throws Exception {
		when(methodsClientMock.usersList(any(UsersListRequest.class))).thenThrow(IOException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogUsersListThrowsSlackApiException() throws Exception {
		when(methodsClientMock.usersList(any(UsersListRequest.class))).thenThrow(SlackApiException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogMethodsOpenDialogThrowsSlackApiException() throws Exception {
		when(methodsClientMock.dialogOpen(any(DialogOpenRequest.class))).thenThrow(SlackApiException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test
	public void testProcessTransaction() throws Exception {
		transactionDialogService.processTransaction(PAYLOAD);

		verify(submittedTransactionProcessorMock).process(PAYLOAD);
	}

	@Test(expected = URISyntaxException.class)
	public void testProcessTransactionProcessorThrowsUriSyntaxException() throws Exception {
		doThrow(URISyntaxException.class).when(submittedTransactionProcessorMock).process(PAYLOAD);

		transactionDialogService.processTransaction(PAYLOAD);
	}
}
