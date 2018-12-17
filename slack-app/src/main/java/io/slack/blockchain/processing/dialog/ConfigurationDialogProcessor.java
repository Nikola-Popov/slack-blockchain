package io.slack.blockchain.processing.dialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.DialogContent;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;
import io.slack.blockchain.processing.payload.ResponsePayloadBuilder;
import io.slack.blockchain.repositories.UserRepository;
import io.slack.blockchain.utils.converters.UserConverter;

@Component
public class ConfigurationDialogProcessor implements DialogProcessor<ConfigurationDialogSubmission> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private ResponsePayloadBuilder configurationDialogResponsePayloadBuilder;

	@Override
	public Payload process(DialogContent<ConfigurationDialogSubmission> dialogContent) {
		userRepository.save(userConverter.convert(dialogContent));

		return configurationDialogResponsePayloadBuilder.buildResponsePayload();
	}
}
