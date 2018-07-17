package io.slack.blockchain.interactive.components.dialogs.elements;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.USER_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionSelectElementConstants.USER_ELEMENT_PLACEHOLDER;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementBuilder;

public class UsersSelectElementBuilderTest {
	private UsersSelectElementBuilder usersSelectElementBuilder;

	private final static List<DialogOption> USERS_DIALOG_OPTIONS = createUsersDialogOptions();

	private static final DialogSelectElement DIALOG_SELECT_ELEMENT = DialogSelectElement.builder()
			.name(USER_ELEMENT_LABEL.toLowerCase()).label(USER_ELEMENT_LABEL).placeholder(USER_ELEMENT_PLACEHOLDER)
			.options(USERS_DIALOG_OPTIONS).build();

	@Before
	public void setup() {
		usersSelectElementBuilder = new UsersSelectElementBuilder(USERS_DIALOG_OPTIONS);
	}

	@Test
	public void testBuild() {
		assertThat(usersSelectElementBuilder.build(), equalTo(DIALOG_SELECT_ELEMENT));
	}

	private static List<DialogOption> createUsersDialogOptions() {
		return unmodifiableList(asList(DialogOption.builder().label("label").value("value").build()));
	}
}
