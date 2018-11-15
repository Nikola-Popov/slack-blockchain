package io.slack.blockchain.services.dialogs;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.MethodsClient;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest;
import com.github.seratch.jslack.api.methods.request.dialog.DialogOpenRequest.DialogOpenRequestBuilder;
import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;
import com.github.seratch.jslack.api.model.dialog.Dialog;

import io.slack.blockchain.commons.configurations.slack.SlackConfigurationProperties;
import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import io.slack.blockchain.interactive.components.dialogs.factories.EmailConfigurationDialogFactory;

@RunWith(MockitoJUnitRunner.class)
public class EmailConfigurationDialogServiceTest {
	private static final String TRIGGER_ID = "triggerId";
	private static final String OAUTH_TOKEN = "oauthToken";

	@InjectMocks
	@Autowired
	private EmailConfigurationDialogService emailConfigurationDialogService;

	@Mock
	private Slack slackMock;

	@Mock
	private EmailConfigurationDialogFactory emailConfigurationDialogFactoryMock;

	@Mock
	private DialogOpenResponseHandler dialogOpenResponseHandlerMock;

	@Mock
	private SlackConfigurationProperties slackConfigurationPropertiesMock;

	@Mock
	private Dialog dialogMock;

	@Mock
	private DialogOpenRequestBuilder dialogOpenRequestBuilderMock;

	@Mock
	private MethodsClient methodsClientMock;

	@Mock
	private DialogOpenRequest dialogOpenRequestMock;

	@Mock
	private DialogOpenResponse dialogOpenResponseMock;

	@Before
	public void setUp() throws Exception {
		when(emailConfigurationDialogFactoryMock.createEmailConfigurationDialog()).thenReturn(dialogMock);
		when(slackMock.methods()).thenReturn(methodsClientMock);
		when(slackConfigurationPropertiesMock.getOauthToken()).thenReturn(OAUTH_TOKEN);
		when(dialogOpenRequestBuilderMock.token(OAUTH_TOKEN)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.triggerId(TRIGGER_ID)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.dialog(dialogMock)).thenReturn(dialogOpenRequestBuilderMock);
		when(dialogOpenRequestBuilderMock.build()).thenReturn(dialogOpenRequestMock);
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenReturn(dialogOpenResponseMock);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogFailsToOpenDialogBecauseOfIOException() throws Exception {
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenThrow(IOException.class);

		emailConfigurationDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogFailsToOpenDialogBecauseOfSlackAPIException() throws Exception {
		when(methodsClientMock.dialogOpen(dialogOpenRequestMock)).thenThrow(IOException.class);

		emailConfigurationDialogService.openDialog(TRIGGER_ID);
	}

	@Test(expected = DialogOpenException.class)
	public void testOpenDialogResponseHandlerThrowsExcetionBecauseOfNotOKResponse() {
		doThrow(DialogOpenException.class).when(dialogOpenResponseHandlerMock)
				.handleDialogOpenResponse(dialogOpenResponseMock);

		emailConfigurationDialogService.openDialog(TRIGGER_ID);
	}

	@Test
	public void testOpenDialog() {
		emailConfigurationDialogService.openDialog(TRIGGER_ID);

		verify(dialogOpenResponseHandlerMock).handleDialogOpenResponse(dialogOpenResponseMock);
	}

}
