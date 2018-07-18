package io.slack.blockchain.interactive.components.dialogs;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogConstants.CREATE_BUTTON_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogConstants.TRANSACTION_DIALOG_TITlE;
import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementFactory;

@Component
public class SlackTransactionsDialogProvider {
	@Autowired
	private DialogSelectElement currencySelectElement;

	@Autowired
	private DialogTextElement amountTextElement;

	@Autowired
	private UsersSelectElementFactory usersSelectElementFactory;

	public Dialog createTransactionsDialog(final List<DialogOption> usersDialogOptions) {
		final DialogSelectElement usersSelectElement = usersSelectElementFactory
				.createUsersSelectElementBasedOn(usersDialogOptions);
		return Dialog.builder().title(TRANSACTION_DIALOG_TITlE).callbackId(TRANSACTION_DIALOG_CALLBACK_ID)
				.elements(asList(amountTextElement, currencySelectElement, usersSelectElement))
				.submitLabel(CREATE_BUTTON_LABEL).build();
	}
}
