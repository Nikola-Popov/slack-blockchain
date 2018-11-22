package io.slack.blockchain.coinbase.broker.domain;

import java.math.BigDecimal;

import io.slack.blockchain.commons.miscellaneous.trading.Currency;
import lombok.Data;

@Data
public class Transaction {
	private final Currency currency;
	private final BigDecimal amount;
	private final String recipient;
	// private final idem
}
