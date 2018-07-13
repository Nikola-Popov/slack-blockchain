package io.slack.blockchain.commons.utils.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.User;
import com.github.seratch.jslack.api.model.dialog.DialogOption;

@Component
public class UserConverter {
	public List<DialogOption> convert(final List<User> users) {
		final List<DialogOption> dialogOptions = new ArrayList<>();
		for (User user : users) {
			dialogOptions.add(DialogOption.builder().label(String.format("%s (%s)", user.getRealName(), user.getName()))
					.value(user.getId()).build());
		}
		return dialogOptions;
	}
}
