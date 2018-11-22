package io.slack.blockchain.commons.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.Attachment.AttachmentBuilder;

@Configuration
public class AttachmentsConfigurations {
	@Bean
	public AttachmentBuilder createAttachmentBuilder() {
		return Attachment.builder();
	}
}
