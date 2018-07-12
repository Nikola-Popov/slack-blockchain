package io.slack.blockchain.interactive.components;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@Component
public class SlackTransactionsDialogFactor {
	public Dialog createTransactionsDialog(final List<DialogOption> usersDialogOptions) {
		final DialogTextElement amountTextElement = DialogTextElement.builder().label("Amount").name("amount")
				.placeholder("Amount of currency you wish to send").maxLength(100).build();

		final DialogSelectElement currencySelectElement = DialogSelectElement.builder().name("currency")
				.label("Currency").placeholder("Choose your preferred currency")
				.options(asList(DialogOption.builder().label("USD").value("usd").build(),
						DialogOption.builder().label("GBP").value("gbp").build(),
						DialogOption.builder().label("BTC").value("btc").build(),
						DialogOption.builder().label("BCH").value("bch").build(),
						DialogOption.builder().label("ETC").value("etc").build()))
				.build();

		final DialogSelectElement usersSelectElement = DialogSelectElement.builder().name("user").label("User")
				.placeholder("Select the user you wish to send the currency to").options(usersDialogOptions).build();

		return Dialog.builder().title("New transaction").callbackId("transaction-dialog")
				.elements(asList(amountTextElement, currencySelectElement, usersSelectElement)).submitLabel("Send")
				.build();

	}
}
