package io.slack.blockchain.interactive.components.dialogs.elements.utils;

import static io.slack.blockchain.domain.dialog.Currency.BCH;
import static io.slack.blockchain.domain.dialog.Currency.BTC;
import static io.slack.blockchain.domain.dialog.Currency.ETC;
import static io.slack.blockchain.domain.dialog.Currency.GBP;
import static io.slack.blockchain.domain.dialog.Currency.USD;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.DialogOption;

@Component
public class CurrenciesProvider {
	public List<DialogOption> provideCurrencies() {
		return unmodifiableList(asList(DialogOption.builder().label(USD.toString()).value(USD.toString()).build(),
				DialogOption.builder().label(GBP.toString()).value(GBP.toString()).build(),
				DialogOption.builder().label(BTC.toString()).value(BTC.toString()).build(),
				DialogOption.builder().label(BCH.toString()).value(BCH.toString()).build(),
				DialogOption.builder().label(ETC.toString()).value(ETC.toString()).build()));
	}
}
