package io.slack.blockchain.interactive.components.dialogs.elements.builders;

import static io.slack.blockchain.domain.dialog.Currency.BCH;
import static io.slack.blockchain.domain.dialog.Currency.BTC;
import static io.slack.blockchain.domain.dialog.Currency.ETC;
import static io.slack.blockchain.domain.dialog.Currency.GBP;
import static io.slack.blockchain.domain.dialog.Currency.USD;
import static java.util.Arrays.asList;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

public class CurrencySelectElementBuilder implements DialogElementBuilder {
	private static final String CURRENCY_ELEMENT_PLACEHOLDER = "Choose your preferred currency";
	private static final String CURRENCY_ELEMENT_LABEL = "Currency";

	@Override
	public DialogElement build() {
		return DialogSelectElement.builder().name(CURRENCY_ELEMENT_LABEL.toLowerCase()).label(CURRENCY_ELEMENT_LABEL)
				.placeholder(CURRENCY_ELEMENT_PLACEHOLDER)
				.options(asList(DialogOption.builder().label(USD.toString()).value(USD.toString()).build(),
						DialogOption.builder().label(GBP.toString()).value(GBP.toString()).build(),
						DialogOption.builder().label(BTC.toString()).value(BTC.toString()).build(),
						DialogOption.builder().label(BCH.toString()).value(BCH.toString()).build(),
						DialogOption.builder().label(ETC.toString()).value(ETC.toString()).build()))
				.build();
	}

}
