package io.slack.blockchain.domain.processing;

import io.slack.blockchain.domain.attachments.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessingResult {
	private Status status;
	private String message;
}
