package io.slack.blockchain.commons.factories;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;

@Component
public class AttachmentResponseFactory {
	public AttachmentResponse createAttachmentResponse(final Attachment attachment) {
		return new AttachmentResponse(attachment);
	}
}
