package io.slack.blockchain.interactive.components.dialogs.elements.builders;

import java.util.List;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersSelectElementBuilder implements DialogElementBuilder {
	private static final String USER_ELEMENT_PLACEHOLDER = "Select the user you wish to send the currency to";
	private static final String USER_ELEMENT_LABEL = "User";

	private final List<DialogOption> usersDialogOptions;

	@Override
	public DialogElement build() {
		return DialogSelectElement.builder().name(USER_ELEMENT_LABEL.toLowerCase()).label(USER_ELEMENT_LABEL)
				.placeholder(USER_ELEMENT_PLACEHOLDER).options(usersDialogOptions).build();
	}

	public static void main(String[] args) {
	}
}
