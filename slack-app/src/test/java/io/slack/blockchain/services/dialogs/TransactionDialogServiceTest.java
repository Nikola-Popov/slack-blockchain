package io.slack.blockchain.services.dialogs;

import static org.mockito.Mockito.doThrow;
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
import org.springframework.beans.factory.annotation.Autowired;

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
import io.slack.blockchain.utils.converters.DialogUserConverter;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDialogServiceTest {
	private static final String OAUTH_TOKEN = "oauthToken";
	private static final String TRIGGER_ID = "triggerId";

	@Autowired
	@InjectMocks
	private TransactionDialogService transactionDialogService;

	@Mock
	private Slack slackMock;

	@Mock
	private DialogUserConverter dialogUserConverterMock;

	@Mock
	private TransactionsDialogFactory slackTransactionDialogFactoryMock;

	@Mock
	private DialogOpenResponseHandler dialogOpenResponseHandlerMock;

	@Mock
	private SlackConfigurationProperties slackConfigurationPropertiesMock;

	@Mock
	private UsersListRequestBuilder usersListRequestBuilderMock;

	@Mock
	private DialogOpenRequestBuilder dialogOpenRequestBuilderMock;

	@Mock
	private MethodsClient methodsClientMock;

	@Mock
	private UsersListRequest usersListRequestMock;

	@Mock
	private UsersListResponse usersListResponseMock;

	@Mock
	private List<User> usersMock;

	@Mock
	private List<DialogOption> usersDialogOptionsMock;

	@Mock
	private Dialog dialogMock;

	@Mock
	private DialogOpenRequest dialogOpenRequestMock;

	@Mock
	private DialogOpenResponse dialogOpenResponseMock;

	@Before
	public void setUp() throws Exception {
		when(slackMock.methods()).thenReturn(methodsClientMock);
		when(slackConfigurationPropertiesMock.getOauthToken()).thenReturn(OAUTH_TOKEN);
		when(usersListRequestBuilderMock.token(OAUTH_TOKEN)).thenReturn(usersListRequestBuilderMock);
		when(usersListRequestBuilderMock.build()).thenReturn(usersListRequestMock);
		when(methodsClientMock.usersList(usersListRequestMock)).thenReturn(usersListResponseMock);
		when(usersListResponseMock.getMembers()).thenReturn(usersMock);
		when(dialogUserConverterMock.convert(usersMock)).thenReturn(usersDialogOptionsMock);
		when(slackTransactionDialogFactoryMock.createTransactionsDialog(usersDialogOptionsMock)).thenReturn(dialogMock);
		when(slackConfigurationPropertiesMock.getOauthToken()).thenReturn(OAUTH_TOKEN);
		when(dialogOpenRequestBuilderMock.token(OAUTH_TOKEN)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.triggerId(TRIGGER_ID)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.dialog(dialogMock)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.build()).thenReturn(dialogOpenRequestMock);
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenReturn(dialogOpenResponseMock);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogSlackUsersRetrievalFailsBecauseOfIOException() throws Exception {
		when(methodsClientMock.usersList(usersListRequestMock)).thenThrow(IOException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogSlackUsersRetrievalFailsBecauseOfSlackAPIException() throws Exception {
		when(methodsClientMock.usersList(usersListRequestMock)).thenThrow(SlackApiException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogFailsBecauseOfIOException() throws Exception {
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenThrow(IOException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogResponseHandlerThrowsExcetionBecauseOfNotOKResponse() {
		doThrow(DialogOpenException.class).when(dialogOpenResponseHandlerMock)
				.handleDialogOpenResponse(dialogOpenResponseMock);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogFailsBecauseOfSlackAPIException() throws Exception {
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenThrow(SlackApiException.class);

		transactionDialogService.openDialog(TRIGGER_ID);
	}

	@Test
	public void testOpenDialog() {
		transactionDialogService.openDialog(TRIGGER_ID);

		verify(dialogOpenResponseHandlerMock).handleDialogOpenResponse(dialogOpenResponseMock);
	}

}
