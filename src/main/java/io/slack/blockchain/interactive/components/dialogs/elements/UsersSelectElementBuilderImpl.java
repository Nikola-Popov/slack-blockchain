package io.slack.blockchain.interactive.components.dialogs.elements;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.USER_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.USER_ELEMENT_PLACEHOLDER;

import java.util.List;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersSelectElementBuilderImpl implements DialogElementBuilder {
	private final List<DialogOption> usersDialogOptions;

	@Override
	public DialogElement build() {
		return DialogSelectElement.builder().name(USER_ELEMENT_LABEL.toLowerCase()).label(USER_ELEMENT_LABEL)
				.placeholder(USER_ELEMENT_PLACEHOLDER).options(usersDialogOptions).build();
	}
}
