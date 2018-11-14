package io.slack.blockchain.services.dialogs;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;

import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;

@RunWith(MockitoJUnitRunner.class)
public class DialogOpenResponseHandlerTest {
	private static final String DIALOG_RESPONSE = "dialogResponse";

	@InjectMocks
	@Autowired
	private DialogOpenResponseHandler dialogOpenResponseHandler;

	@Mock
	private DialogOpenResponse dialogOpenResponseMock;

	@Before
	public void setUp() {
		when(dialogOpenResponseMock.toString()).thenReturn(DIALOG_RESPONSE);
	}

	@Test(expected = DialogOpenException.class)
	public void testHandleDialogOpenResponseFailsBecauseOfNotOKResponse() {
		when(dialogOpenResponseMock.isOk()).thenReturn(false);

		dialogOpenResponseHandler.handleDialogOpenResponse(dialogOpenResponseMock);
	}
}
