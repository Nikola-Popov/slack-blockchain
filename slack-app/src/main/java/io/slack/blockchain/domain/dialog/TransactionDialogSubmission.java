package io.slack.blockchain.domain.dialog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDialogSubmission {
	private int amount;
	private Currency currency;
	private String user;
	private String responseUrl;
}
