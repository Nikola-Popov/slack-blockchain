package io.slack.blockchain.domain.messages;

import static java.util.Arrays.asList;

import java.util.List;

import lombok.Data;

@Data
public class MessageResponse {
	private String text;
	private List<AttachmentAction> attachments;

	public MessageResponse(final AttachmentAction attachmentAction) {
		attachments = asList(attachmentAction);
	}
}
