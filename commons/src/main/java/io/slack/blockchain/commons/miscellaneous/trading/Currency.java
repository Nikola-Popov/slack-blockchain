package io.slack.blockchain.commons.miscellaneous.trading;

import lombok.Getter;

@Getter
public enum Currency {
	Bitcoin("Bitcoin"), BitcoinCash("Bitcoin Cash"), Ethereum("Ethereum"), EthereumClassic(
			"Ethereum Classic"), LiteCoin("LiteCoin");

	private String currencyDisplayName;

	private Currency(String currencyDisplayName) {
		this.currencyDisplayName = currencyDisplayName;
	}
}