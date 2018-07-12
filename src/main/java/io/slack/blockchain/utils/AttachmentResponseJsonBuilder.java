package io.slack.blockchain.utils;

import static java.lang.String.format;

import io.slack.blockchain.domain.AttachmentResponse;

public class AttachmentResponseJsonBuilder {
	private static final String RESPONSE_PATTERN = "{\"attachments\":[{\"text\":\"%s\", \"color\":\"%s\",\"footer\":\"Be careful! Every transaction you make is at your own risk.\"}]}";

	public static String buildJsonResponse(final AttachmentResponse attachmentResponse) {
		return format(RESPONSE_PATTERN, attachmentResponse.getText(), attachmentResponse.getColor());
	}
}
