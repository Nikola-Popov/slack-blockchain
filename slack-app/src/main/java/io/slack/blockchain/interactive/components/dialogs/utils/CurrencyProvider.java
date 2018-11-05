package io.slack.blockchain.interactive.components.dialogs.utils;

import static com.github.seratch.jslack.api.model.dialog.DialogOption.builder;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.Bitcoin;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.BitcoinCash;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.Ethereum;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.EthereumClassic;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.LiteCoin;
import static java.util.Arrays.asList;

import java.util.List;

import com.github.seratch.jslack.api.model.dialog.DialogOption;

public class CurrencyProvider {
	public List<DialogOption> provideSupportedCurrencies() {
		return (asList(
				builder().label(Bitcoin.getCurrencyDisplayName()).value(Bitcoin.getCurrencyDisplayName()).build(),
				builder().label(BitcoinCash.getCurrencyDisplayName()).value(BitcoinCash.getCurrencyDisplayName())
						.build(),
				builder().label(Ethereum.getCurrencyDisplayName()).value(Ethereum.getCurrencyDisplayName()).build(),
				builder().label(EthereumClassic.getCurrencyDisplayName())
						.value(EthereumClassic.getCurrencyDisplayName()).build(),
				builder().label(LiteCoin.getCurrencyDisplayName()).value(LiteCoin.getCurrencyDisplayName()).build()));
	}
}
