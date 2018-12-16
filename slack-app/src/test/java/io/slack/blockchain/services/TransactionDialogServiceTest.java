package io.slack.blockchain.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.MethodsClient;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest.DialogOpenRequestBuilder;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest.UsersListRequestBuilder;
import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;
import com.github.seratch.jslack.api.methods.response.users.UsersListResponse;
import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

import io.slack.blockchain.commons.configurations.SlackConfigurationProperties;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.interactive.components.dialogs.factories.TransactionsDialogFactory;
import io.slack.blockchain.services.dialogs.DialogOpenResponseHandler;
import io.slack.blockchain.services.dialogs.TransactionDialogService;
import io.slack.blockchain.utils.converters.DialogUserConverter;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDialogServiceTest {
	private static final String SLACK_OUATH_TOKEN = "slack-oauth-token";
	private static final String TRIGGER_ID = "triggerId";

	@InjectMocks
	private TransactionDialogService transactionDialogService;

	@Mock
	private DialogUserConverter dialogUserConverterMock;

	@Mock
	private TransactionsDialogFactory slackTransactionDialogFactoryMock;

	@Mock
	private Slack slackMock;

	@Mock
	private MethodsClient methodsClientMock;

	@Mock
	private UsersListResponse usersListResponseMock;

	@Mock
	private List<User> usersMock;

	@Mock
	private List<DialogOption> usersDialogOptionsMock;

	@Mock
	private DialogOpenResponse dialogOpenResponseMock;

	@Mock
	private SlackConfigurationProperties slackConfigurationPropertiesMock;

	@Mock
	private UsersListRequestBuilder usersListRequestBuilderMock;

	@Mock
	private UsersListRequest usersListRequestMock;

	@Mock
	private DialogOpenResponseHandler dialogOpenResponseHandlerMock;

	@Mock
	private DialogOpenRequestBuilder dialogOpenRequestBuilderMock;

	@Mock
	private DialogOpenRequest dialogOpenRequestMock;

	@Mock
	private Dialog dialogMock;

	@Before
	public void setup() throws Exception {
		when(slackConfigurationPropertiesMock.getOauthToken()).thenReturn(SLACK_OUATH_TOKEN);
		when(usersListRequestBuilderMock.token(SLACK_OUATH_TOKEN)).thenReturn(usersListRequestBuilderMock);
		when(usersListRequestBuilderMock.build()).thenReturn(usersListRequestMock);
		when(slackTransactionDialogFactoryMock.createTransactionsDialog(usersDialogOptionsMock)).thenReturn(dialogMock);

		when(slackMock.methods()).thenReturn(methodsClientMock);
		when(methodsClientMock.usersList(usersListRequestMock)).thenReturn(usersListResponseMock);
		when(dialogOpenRequestBuilderMock.token(SLACK_OUATH_TOKEN)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.triggerId(TRIGGER_ID)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.dialog(dialogMock)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.build()).thenReturn(dialogOpenRequestMock);
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenReturn(dialogOpenResponseMock);
		when(usersListResponseMock.getMembers()).thenReturn(usersMock);
		when(dialogUserConverterMock.convert(usersMock)).thenReturn(usersDialogOptionsMock);
	}

	@Test
	public void testOpenTransactionDialog() throws Exception {
		transactionDialogService.openDialog(TRIGGER_ID);

		verify(dialogOpenResponseHandlerMock).handleDialogOpenResponse(dialogOpenResponseMock);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogUsersListThrowsIOException() throws Exception {
		when(methodsClientMock.usersList(usersListRequestMock)).thenThrow(IOException.class);

		transactionDialogService.openDialog(TRIGGER_ID);

		verifyNoInteractionsAfterUsersListFailed();
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogUsersListThrowsSlackApiException() throws Exception {
		doThrow(SlackApiException.class).when(methodsClientMock).usersList(usersListRequestMock);

		transactionDialogService.openDialog(TRIGGER_ID);

		verifyNoInteractionsAfterUsersListFailed();
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogMethodsOpenDialogThrowsSlackApiException() throws Exception {
		doThrow(SlackApiException.class).when(methodsClientMock).dialogOpen(dialogOpenRequestMock);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenTransactionDialogUnableToHandleOpenedDialogResponse() {
		doThrow(DialogOpenException.class).when(dialogOpenResponseHandlerMock)
				.handleDialogOpenResponse(dialogOpenResponseMock);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	private void verifyNoInteractionsAfterUsersListFailed() {
		verify(dialogUserConverterMock, never()).convert(usersMock);
		verify(slackTransactionDialogFactoryMock, never()).createTransactionsDialog(usersDialogOptionsMock);
		verify(dialogOpenResponseHandlerMock, never()).handleDialogOpenResponse(dialogOpenResponseMock);
		verify(slackConfigurationPropertiesMock, never()).getOauthToken();
		verify(dialogOpenRequestBuilderMock, never()).triggerId(TRIGGER_ID);
		verify(dialogOpenRequestBuilderMock, never()).dialog(dialogMock);
		verify(dialogOpenRequestBuilderMock, never()).build();
		verify(dialogOpenResponseHandlerMock, never()).handleDialogOpenResponse(dialogOpenResponseMock);
	}
}
