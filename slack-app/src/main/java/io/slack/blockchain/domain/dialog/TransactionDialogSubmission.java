package io.slack.blockchain.domain.dialog;

import io.slack.blockchain.commons.miscellaneous.trading.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDialogSubmission {
	private int amount;
	private Currency currency;
	private String user;
}
