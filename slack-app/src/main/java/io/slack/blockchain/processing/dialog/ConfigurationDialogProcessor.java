package io.slack.blockchain.processing.dialog;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.Action;
import com.github.seratch.jslack.api.model.Action.ActionBuilder;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.Attachment.AttachmentBuilder;
import com.github.seratch.jslack.api.model.Confirmation.ConfirmationBuilder;
import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.domain.processing.ProcessingResult.ProcessingResultBuilder;
import io.slack.blockchain.repositories.UserRepository;
import io.slack.blockchain.utils.converters.UserConverter;

@Component
public class ConfigurationDialogProcessor implements DialogProcessor<ConfigurationDialogContent> {
	private static final String BUTTON_NAME = "coinbase_confirmation";
	private static final String CONTINUE_BUTTON_LABEL = "Continue";
	private static final String REDIRECT_TITLE = "About to redirect to Coinbase";
	private static final String PRIMARY_STYLE = "primary";
	private static final String BUTTON_TEXT = "Confirm your Coinbase account";
	private static final String SUCCESSFUL_CONFIGURATION_MESSAGE = "Great, your email was successfully configured! There is only one more step to go.\n In order to be able to make transaction you must be authorized by Coinbase. Do this by clicking the button below.";
	private static final String REDIRECT_DISPLAY_MESSAGE = "We are going to redirect you to coinbase.com in order be authorized there, but don`t worry!\nIt takes just a few clicks.\nThis will allow you to make secure transactions via the app.";

	@Autowired
	private ActionBuilder actionBuilder;

	@Autowired
	private AttachmentBuilder attachmentBuilder;

	@Autowired
	private ConfirmationBuilder confirmationBuilder;

	@Autowired
	private ProcessingResultBuilder processingResultBuilder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter useConverter;

	@Override
	public ProcessingResult process(ConfigurationDialogContent configurationDialogContent) {
		userRepository.save(useConverter.convert(configurationDialogContent));

		Action action = actionBuilder.name(BUTTON_NAME).style(PRIMARY_STYLE).text(BUTTON_TEXT)
				.url("https://api.slack.com/docs/message-attachments#link_buttons").confirm(confirmationBuilder
						.title(REDIRECT_TITLE).text(REDIRECT_DISPLAY_MESSAGE).ok_text(CONTINUE_BUTTON_LABEL).build())
				.build();
		Attachment attachment = attachmentBuilder.actions(asList(action)).msgUnfurl(true)
				.fallback("demo test dunno about this").build();
		Payload payload = Payload.builder().text(SUCCESSFUL_CONFIGURATION_MESSAGE).attachments(asList(attachment))
				.build();

		return processingResultBuilder.payload(payload).build();
	}

}
