package io.slack.blockchain.domain.processing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessingResult {
	private String statusColor;
	private String message;
}
