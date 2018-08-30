package io.slack.blockchain.interactive.components.dialogs.elements;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.CurrencyDialogSelectElementConstants.USER_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.CurrencyDialogSelectElementConstants.USER_ELEMENT_PLACEHOLDER;

import java.util.List;

import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

public class UsersSelectElement {
	public DialogSelectElement constructBasedOn(List<DialogOption> usersDialogOptions) {
		return DialogSelectElement.builder().name(USER_ELEMENT_LABEL.toLowerCase()).label(USER_ELEMENT_LABEL)
				.placeholder(USER_ELEMENT_PLACEHOLDER).options(usersDialogOptions).build();
	}
}
