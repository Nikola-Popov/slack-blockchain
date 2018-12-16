package io.slack.blockchain.processing.dialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.processing.payload.ResponsePayloadBuilder;
import io.slack.blockchain.repositories.UserRepository;
import io.slack.blockchain.utils.converters.UserConverter;

@Component
public class ConfigurationDialogProcessor implements DialogProcessor<ConfigurationDialogContent> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private ResponsePayloadBuilder configurationDialogResponsePayloadBuilder;

	@Override
	public Payload process(ConfigurationDialogContent configurationDialogContent) {
		userRepository.save(userConverter.convert(configurationDialogContent));

		return configurationDialogResponsePayloadBuilder.buildResponsePayload();
	}

}
