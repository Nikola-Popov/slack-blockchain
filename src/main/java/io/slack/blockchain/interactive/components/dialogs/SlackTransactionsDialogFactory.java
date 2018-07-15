package io.slack.blockchain.interactive.components.dialogs;

import static com.github.seratch.jslack.api.model.dialog.DialogSubType.NUMBER;
import static io.slack.blockchain.domain.dialog.Currency.BCH;
import static io.slack.blockchain.domain.dialog.Currency.BTC;
import static io.slack.blockchain.domain.dialog.Currency.ETC;
import static io.slack.blockchain.domain.dialog.Currency.GBP;
import static io.slack.blockchain.domain.dialog.Currency.USD;
import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement.DialogSelectElementBuilder;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@Component
public class SlackTransactionsDialogFactory {
	private static final String AMOUNT_ELEMENT_LABEL = "Amount of currency you wish to send";
	private static final String AMOUNT_LABEL = "Amount";

	private static final String USER_ELEMENT_PLACEHOLDER = "Select the user you wish to send the currency to";
	private static final String USER_ELEMENT_LABEL = "User";

	private static final String CURRENCY_ELEMENT_PLACEHOLDER = "Choose your preferred currency";
	private static final String CURRENCY_ELEMENT_LABEL = "Currency";

	private static final int TEXT_ELEMENT_MAX_ELEMENT_LENGTH = 100;
	private static final String CREATE_BUTTON_LABEL = "Create";
	private static final String TRANSACTION_DIALOG_CALLBACK_ID = "transaction-dialog";
	private static final String TRANSACTION_DIALOG_TITlE = "New transaction";

	public Dialog createTransactionsDialog(final List<DialogOption> usersDialogOptions) {
		final DialogTextElement amountTextElement = buildAmountTextElement();

		final DialogSelectElement currencySelectElement = buildCurrencySelectElement();

		final DialogSelectElement usersSelectElement = buildUsersSelectElement(usersDialogOptions);

		return Dialog.builder().title(TRANSACTION_DIALOG_TITlE).callbackId(TRANSACTION_DIALOG_CALLBACK_ID)
				.elements(asList(amountTextElement, currencySelectElement, usersSelectElement))
				.submitLabel(CREATE_BUTTON_LABEL).build();
	}

	private DialogTextElement buildAmountTextElement() {
		return DialogTextElement.builder().label(AMOUNT_LABEL).name(AMOUNT_LABEL.toLowerCase())
				.placeholder(AMOUNT_ELEMENT_LABEL).maxLength(TEXT_ELEMENT_MAX_ELEMENT_LENGTH).subtype(NUMBER).build();
	}

	private DialogSelectElement buildCurrencySelectElement() {
		return createDefaultDialogSelectElementBuilder(CURRENCY_ELEMENT_LABEL.toLowerCase(), CURRENCY_ELEMENT_LABEL,
				CURRENCY_ELEMENT_PLACEHOLDER)
						.options(asList(DialogOption.builder().label(USD.toString()).value(USD.toString()).build(),
								DialogOption.builder().label(GBP.toString()).value(GBP.toString()).build(),
								DialogOption.builder().label(BTC.toString()).value(BTC.toString()).build(),
								DialogOption.builder().label(BCH.toString()).value(BCH.toString()).build(),
								DialogOption.builder().label(ETC.toString()).value(ETC.toString()).build()))
						.build();
	}

	private DialogSelectElement buildUsersSelectElement(final List<DialogOption> usersDialogOptions) {
		return createDefaultDialogSelectElementBuilder(USER_ELEMENT_LABEL.toLowerCase(), USER_ELEMENT_LABEL,
				USER_ELEMENT_PLACEHOLDER).options(usersDialogOptions).build();
	}

	private DialogSelectElementBuilder createDefaultDialogSelectElementBuilder(final String name, final String value,
			final String placeholder) {
		return DialogSelectElement.builder().name(name).label(value).placeholder(placeholder);
	}
}
