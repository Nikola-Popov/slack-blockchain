package io.slack.blockchain.utils.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogOption.DialogOptionBuilder;

@Component
public class DialogUserConverter {
	@Autowired
	private DialogOptionBuilder dialogOptionBuilder;

	public List<DialogOption> convert(final List<User> users) {
		final List<DialogOption> dialogOptions = new ArrayList<>();
		for (User user : users) {
			dialogOptions.add(dialogOptionBuilder.label(String.format("%s (%s)", user.getRealName(), user.getName()))
					.value(user.getId()).build());
		}
		return dialogOptions;
	}
}
