package io.slack.blockchain.commons.configurations.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.seratch.jslack.api.model.Action;
import com.github.seratch.jslack.api.model.Action.ActionBuilder;
import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.Attachment.AttachmentBuilder;
import com.github.seratch.jslack.api.model.Confirmation;
import com.github.seratch.jslack.api.model.Confirmation.ConfirmationBuilder;

@Configuration
public class AttachmentsConfigurations {
	@Bean
	public AttachmentBuilder createAttachmentBuilder() {
		return Attachment.builder();
	}

	@Bean
	public ActionBuilder createActionBuilder() {
		return Action.builder();
	}

	@Bean
	public ConfirmationBuilder createConfirmationBuilder() {
		return Confirmation.builder();
	}
}
