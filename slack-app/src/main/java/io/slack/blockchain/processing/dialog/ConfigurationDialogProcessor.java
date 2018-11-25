package io.slack.blockchain.processing.dialog;

import static io.slack.blockchain.domain.messages.AttachmentStatusColor.WARNING;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.Action;
import com.github.seratch.jslack.api.model.Action.ActionBuilder;
import com.github.seratch.jslack.api.model.Attachment.AttachmentBuilder;
import com.github.seratch.jslack.api.model.Confirmation.ConfirmationBuilder;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.domain.processing.ProcessingResult.ProcessingResultBuilder;

@Component
public class ConfigurationDialogProcessor implements DialogProcessor<ConfigurationDialogContent> {
	private static final String BUTTON_NAME = "coinbase_confirmation";
	private static final String CONTINUE_BUTTON_LABEL = "Continue";
	private static final String PRIVACY_POLICY = "Privacy policy";
	private static final String PRIMARY = "primary";
	private static final String BUTTON_TEXT = "Confirm your Coinbase account";
	private static final String PRIVACY_POLICY_DISPLAY_MESSAGE = "Don`t worry! No private data is stored in the app itself.\n We just delegate this response to coinbase as they have proved to be trustworthy and secure!";
	private static final String SUCCESSFUL_CONFIGURATION_MESSAGE = "Great, your email was successfully configured! There is only one more step to go.\n In order to be able to make transaction you must be authorized by Coinbase. Do this by clicking the button below";

	private ProcessingResultBuilder processingResultBuilder;

	@Autowired
	private ActionBuilder actionBuilder;

	@Autowired
	private AttachmentBuilder attachmentBuilder;

	@Autowired
	private ConfirmationBuilder confirmationBuilder;

	@Override
	public ProcessingResult process(ConfigurationDialogContent configurationDialogContent) {
		/*
		 * TODO 1)save in DB IF SUCCESS continue with execution listed below; ELSE error
		 */

		Action action = actionBuilder.name(BUTTON_NAME).text(BUTTON_TEXT).url("").style(PRIMARY)
				.confirm(confirmationBuilder.title(PRIVACY_POLICY).text(PRIVACY_POLICY_DISPLAY_MESSAGE)
						.ok_text(CONTINUE_BUTTON_LABEL).build())
				.build();

		return processingResultBuilder.attachment(attachmentBuilder.actions(asList(action)).build())
				.statusColor(WARNING).message(SUCCESSFUL_CONFIGURATION_MESSAGE).build();
	}

}
