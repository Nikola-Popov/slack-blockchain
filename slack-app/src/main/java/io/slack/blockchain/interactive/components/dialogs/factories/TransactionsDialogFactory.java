package io.slack.blockchain.interactive.components.dialogs.factories;

import static io.slack.blockchain.commons.configurations.slack.BeanConfigurationConstants.AMOUNT_DIALOG_TEXT_ELEMENT;
import static io.slack.blockchain.commons.configurations.slack.BeanConfigurationConstants.CURRENCY_SELECT_DIALOG_TEXT_ELEMENT;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.CREATE_BUTTON_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_TITlE;
import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.Dialog.DialogBuilder;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementFactory;

@Component
public class TransactionsDialogFactory {
	@Autowired
	@Qualifier(CURRENCY_SELECT_DIALOG_TEXT_ELEMENT)
	private DialogSelectElement currencySelectElement;

	@Autowired
	@Qualifier(AMOUNT_DIALOG_TEXT_ELEMENT)
	private DialogTextElement amountTextElement;

	@Autowired
	private UsersSelectElementFactory usersSelectElementFactory;

	@Autowired
	private DialogBuilder dialogBuilder;

	public Dialog createTransactionsDialog(final List<DialogOption> usersDialogOptions) {
		final DialogSelectElement usersSelectElement = usersSelectElementFactory
				.createUsersSelectElementBasedOn(usersDialogOptions);
		return dialogBuilder.title(TRANSACTION_DIALOG_TITlE).callbackId(TRANSACTION_DIALOG_CALLBACK_ID)
				.elements(asList(amountTextElement, currencySelectElement, usersSelectElement))
				.submitLabel(CREATE_BUTTON_LABEL).build();
	}
}
