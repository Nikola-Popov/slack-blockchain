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

import io.slack.blockchain.interactive.components.dialogs.elements.AmountDialogTextElementBuilderImpl;
import io.slack.blockchain.interactive.components.dialogs.elements.CurrencySelectElementBuilderImpl;
import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementBuilderImpl;
import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementBuilderFactory;

@Component
public class SlackTransactionsDialogProvider {
	@Autowired
	private CurrencySelectElementBuilderImpl currencySelectElementBuilder;

	@Autowired
	private AmountDialogTextElementBuilderImpl amountDialogTextElementBuilder;

	@Autowired
	private UsersSelectElementBuilderFactory usersSelectElementBuilderFactory;

	public Dialog createTransactionsDialog(final List<DialogOption> usersDialogOptions) {
		final UsersSelectElementBuilderImpl usersSelectElementBuilder = usersSelectElementBuilderFactory
				.createUsersSelectElementBuilder(usersDialogOptions);
		return Dialog.builder().title(TRANSACTION_DIALOG_TITlE).callbackId(TRANSACTION_DIALOG_CALLBACK_ID)
				.elements(asList(amountDialogTextElementBuilder.build(), currencySelectElementBuilder.build(),
						usersSelectElementBuilder.build()))
				.submitLabel(CREATE_BUTTON_LABEL).build();
	}
}
