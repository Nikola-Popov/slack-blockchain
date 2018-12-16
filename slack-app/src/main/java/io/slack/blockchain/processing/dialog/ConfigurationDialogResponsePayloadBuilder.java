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
import com.github.seratch.jslack.api.webhook.Payload.PayloadBuilder;

import io.slack.blockchain.coinbase.broker.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import io.slack.blockchain.processing.payload.ResponsePayloadBuilder;

@Component
public class ConfigurationDialogResponsePayloadBuilder implements ResponsePayloadBuilder {
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
	private PayloadBuilder payloadBuilder;

	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	@Override
	public Payload buildResponsePayload() {
		final Action action = actionBuilder.name(BUTTON_NAME).style(PRIMARY_STYLE).text(BUTTON_TEXT)
				.url(coinbaseAuthorizationEndpointBuilderUtil.buildAuthorizationEndpoint("state"))
				.confirm(confirmationBuilder.title(REDIRECT_TITLE).text(REDIRECT_DISPLAY_MESSAGE)
						.ok_text(CONTINUE_BUTTON_LABEL).build())
				.build();
		final Attachment attachment = attachmentBuilder.actions(asList(action)).fallback(REDIRECT_DISPLAY_MESSAGE)
				.build();
		return payloadBuilder.text(SUCCESSFUL_CONFIGURATION_MESSAGE).attachments(asList(attachment)).build();
	}

}
