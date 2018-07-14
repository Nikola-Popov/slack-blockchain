package io.slack.blockchain.domain.attachments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attachment {
	private final String text;
	private final Status status;
}
