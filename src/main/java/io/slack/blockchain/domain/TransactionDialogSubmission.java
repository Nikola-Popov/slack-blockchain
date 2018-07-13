package io.slack.blockchain.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDialogSubmission {
	private int amount;
	private String currency;
	private String user;
}
