package io.slack.blockchain.interactive.components.dialogs.factories;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_TITlE;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.SUBMIT_BUTTON_LABEL;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.Dialog.DialogBuilder;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@RunWith(MockitoJUnitRunner.class)
public class EmailConfigurationDialogFactoryTest {
	@Autowired
	@InjectMocks
	private EmailConfigurationDialogFactory emailConfigurationDialogFactory;

	@Mock
	private DialogTextElement emailConfigurationTextElementMock;

	@Mock
	private DialogBuilder dialogBuilderMock;

	@Mock
	private Dialog dialogMock;

	@Before
	public void setUp() {
		when(dialogBuilderMock.title(CONFIGURATION_DIALOG_TITlE)).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.callbackId(CONFIGURATION_DIALOG_CALLBACK_ID)).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.elements(asList(emailConfigurationTextElementMock))).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.submitLabel(SUBMIT_BUTTON_LABEL)).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.build()).thenReturn(dialogMock);
	}

	@Test
	public void testCreateEmailConfigurationDialog() {
		emailConfigurationDialogFactory.createEmailConfigurationDialog();

		verify(dialogBuilderMock).title(CONFIGURATION_DIALOG_TITlE);
		verify(dialogBuilderMock).callbackId(CONFIGURATION_DIALOG_CALLBACK_ID);
		verify(dialogBuilderMock).submitLabel(SUBMIT_BUTTON_LABEL);
		verify(dialogBuilderMock).elements(asList(emailConfigurationTextElementMock));
	}
}
