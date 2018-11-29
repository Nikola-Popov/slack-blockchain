package io.slack.blockchain.domain.processing;

import com.github.seratch.jslack.api.webhook.Payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessingResult {
	private String statusColor;
	private String message;
	private Payload payload;
}
