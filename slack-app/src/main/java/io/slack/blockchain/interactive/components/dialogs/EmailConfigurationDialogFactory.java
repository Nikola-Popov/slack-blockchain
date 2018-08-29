package io.slack.blockchain.interactive.components.dialogs;

import static io.slack.blockchain.commons.configurations.slack.BeanConfigurationConstants.EMAIL_DIALOG_TEXT_ELEMENT;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_TITlE;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.SUBMIT_BUTTON_LABEL;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@Component
public class EmailConfigurationDialogFactory {
	@Autowired
	@Qualifier(EMAIL_DIALOG_TEXT_ELEMENT)
	private DialogTextElement emailConfigurationTextElement;

	public Dialog createEmailConfigurationDialog() {
		return Dialog.builder().title(CONFIGURATION_DIALOG_TITlE).callbackId(CONFIGURATION_DIALOG_CALLBACK_ID)
				.elements(asList(emailConfigurationTextElement)).submitLabel(SUBMIT_BUTTON_LABEL).build();
	}
}
