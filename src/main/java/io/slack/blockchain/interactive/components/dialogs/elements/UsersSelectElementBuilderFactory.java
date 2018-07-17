package io.slack.blockchain.interactive.components.dialogs.elements;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.DialogOption;

@Component
public class UsersSelectElementBuilderFactory {
	public UsersSelectElementBuilder createUsersSelectElementBuilder(final List<DialogOption> usersDialogOptions) {
		return new UsersSelectElementBuilder(usersDialogOptions);
	}
}
