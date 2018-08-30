package io.slack.blockchain.domain.dialog;

import io.slack.blockchain.commons.miscellaneous.trading.Currency;
import lombok.Data;

@Data
public class TransactionDialogSubmission {
	private int amount;
	private Currency currency;
	private String user;
}
