package io.slack.blockchain.interactive.components.dialogs;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

@Component
public class SlackTransactionsDialogDisplayer {

	private static final String CREATE_BUTTON_LABEL = "Create";
	private static final String TRANSACTION_DIALOG_CALLBACK_ID = "transaction-dialog";
	private static final String TRANSACTION_DIALOG_TITlE = "New transaction";

	public Dialog createTransactionsDialog(final List<DialogOption> usersDialogOptions) {

		return Dialog.builder().title(TRANSACTION_DIALOG_TITlE).callbackId(TRANSACTION_DIALOG_CALLBACK_ID)
				.elements(asList(amountTextElement, currencySelectElement, usersSelectElement))
				.submitLabel(CREATE_BUTTON_LABEL).build();
	}
}
