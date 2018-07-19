package io.slack.blockchain.interactive.components.dialogs.elements;

import java.util.List;

import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

public class UsersSelectElementFactory {
	public DialogSelectElement createUsersSelectElementBasedOn(final List<DialogOption> usersDialogOptions) {
		return new UsersSelectElement().constructBasedOn(usersDialogOptions);
	}
}
