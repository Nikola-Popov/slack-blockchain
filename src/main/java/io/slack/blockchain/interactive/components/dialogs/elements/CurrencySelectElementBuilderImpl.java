package io.slack.blockchain.interactive.components.dialogs.elements;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.CURRENCY_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.CURRENCY_ELEMENT_PLACEHOLDER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

import io.slack.blockchain.interactive.components.dialogs.elements.utils.CurrenciesProvider;

@Component
public class CurrencySelectElementBuilderImpl implements DialogElementBuilder {
	@Autowired
	private CurrenciesProvider currenciesProvider;

	@Override
	public DialogElement build() {
		return DialogSelectElement.builder().name(CURRENCY_ELEMENT_LABEL.toLowerCase()).label(CURRENCY_ELEMENT_LABEL)
				.placeholder(CURRENCY_ELEMENT_PLACEHOLDER).options(currenciesProvider.provideCurrencies()).build();
	}
}
