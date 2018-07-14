package io.slack.blockchain.domain.attachments;

import static java.util.Arrays.asList;

import java.util.List;

import lombok.Data;

@Data
public class AttachmentResponse {
	private final List<Attachment> attachments;

	public AttachmentResponse(final Attachment attachment) {
		attachments = asList(attachment);
	}
}
