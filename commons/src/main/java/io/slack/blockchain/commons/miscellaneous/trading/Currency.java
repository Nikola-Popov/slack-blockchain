package io.slack.blockchain.commons.miscellaneous.trading;

import lombok.Getter;

@Getter
public enum Currency {
	BITCOIN("Bitcoin", "BTC"), BITCOIN_CASH("Bitcoin Cash", "BCH"), ETHEREUM("Ethereum",
			"ETH"), ETHEREUM_CLASSIC("Ethereum Classic", "ETC"), LITE_COIN("LiteCoin", "LTC");

	private final String currencyDisplayName;
	private final String currencyShortName;

	private Currency(final String currencyDisplayName, final String currencyShortName) {
		this.currencyDisplayName = currencyDisplayName;
		this.currencyShortName = currencyShortName;
	}
}