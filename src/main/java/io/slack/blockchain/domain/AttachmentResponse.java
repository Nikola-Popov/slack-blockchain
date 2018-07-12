package io.slack.blockchain.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AttachmentResponse {
	private String text;
	private String color;
}
