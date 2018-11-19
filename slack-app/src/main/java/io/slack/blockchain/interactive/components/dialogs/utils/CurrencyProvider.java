package io.slack.blockchain.interactive.components.dialogs.utils;

import static com.github.seratch.jslack.api.model.dialog.DialogOption.builder;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.BITCOIN;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.BITCOIN_CASH;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.ETHEREUM;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.ETHEREUM_CLASSIC;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.LITE_COIN;
import static java.util.Arrays.asList;

import java.util.List;

import com.github.seratch.jslack.api.model.dialog.DialogOption;

public class CurrencyProvider {
	public List<DialogOption> provideSupportedCurrencies() {
		return (asList(
				builder().label(BITCOIN.getCurrencyDisplayName()).value(BITCOIN.getCurrencyDisplayName()).build(),
				builder().label(BITCOIN_CASH.getCurrencyDisplayName()).value(BITCOIN_CASH.getCurrencyDisplayName())
						.build(),
				builder().label(ETHEREUM.getCurrencyDisplayName()).value(ETHEREUM.getCurrencyDisplayName()).build(),
				builder().label(ETHEREUM_CLASSIC.getCurrencyDisplayName())
						.value(ETHEREUM_CLASSIC.getCurrencyDisplayName()).build(),
				builder().label(LITE_COIN.getCurrencyDisplayName()).value(LITE_COIN.getCurrencyDisplayName()).build()));
	}
}
