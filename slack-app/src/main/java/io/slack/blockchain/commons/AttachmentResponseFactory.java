package io.slack.blockchain.commons;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.Attachment;

import io.slack.blockchain.domain.attachments.AttachmentResponse;

@Component
public class AttachmentResponseFactory {
	public AttachmentResponse createAttachmentResponse(final Attachment attachment) {
		return new AttachmentResponse(attachment);
	}
}
