package io.slack.blockchain.commons.miscellaneous.trading;

import lombok.Getter;

@Getter
public enum Currency {
	BITCOIN("Bitcoin"), BITCOIN_CASH("Bitcoin Cash"), ETHEREUM("Ethereum"), ETHEREUM_CLASSIC(
			"Ethereum Classic"), LITE_COIN("LiteCoin");

	private String currencyDisplayName;

	private Currency(String currencyDisplayName) {
		this.currencyDisplayName = currencyDisplayName;
	}
}