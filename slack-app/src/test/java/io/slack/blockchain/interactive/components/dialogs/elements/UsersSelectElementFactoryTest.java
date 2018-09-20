package io.slack.blockchain.interactive.components.dialogs.elements;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.CurrencyDialogSelectElementConstants.USER_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.CurrencyDialogSelectElementConstants.USER_ELEMENT_PLACEHOLDER;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;

public class UsersSelectElementFactoryTest {
	private static final List<DialogOption> USERS_DIALOG_OPTIONS = asList(
			DialogOption.builder().label("label").value("value").build());
	private static final DialogSelectElement USERS_SELECT_ELEMENT = DialogSelectElement.builder()
			.name(USER_ELEMENT_LABEL.toLowerCase()).label(USER_ELEMENT_LABEL).placeholder(USER_ELEMENT_PLACEHOLDER)
			.options(USERS_DIALOG_OPTIONS).build();

	private UsersSelectElementFactory usersSelectElementFactory;

	@Before
	public void setUp() {
		usersSelectElementFactory = new UsersSelectElementFactory();
	}

	@Test
	public void testCreateUsersSelectElementBasedOn() {
		assertThat(usersSelectElementFactory.createUsersSelectElementBasedOn(USERS_DIALOG_OPTIONS),
				equalTo(USERS_SELECT_ELEMENT));
	}
}
