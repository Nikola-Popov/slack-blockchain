package io.slack.blockchain.interactive.components.dialogs.factories;

import static io.slack.blockchain.commons.configurations.slack.BeanConfigurationConstants.AMOUNT_DIALOG_TEXT_ELEMENT;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.CREATE_BUTTON_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_TITlE;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.seratch.jslack.api.model.dialog.Dialog;
import com.github.seratch.jslack.api.model.dialog.Dialog.DialogBuilder;
import com.github.seratch.jslack.api.model.dialog.DialogOption;
import com.github.seratch.jslack.api.model.dialog.DialogSelectElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

import io.slack.blockchain.commons.configurations.slack.BeanConfigurationConstants;
import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementFactory;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsDialogFactoryTest {
	@InjectMocks
	@Autowired
	private TransactionsDialogFactory transactionsDialogFactory;

	@Mock
	@Qualifier(BeanConfigurationConstants.CURRENCY_SELECT_DIALOG_TEXT_ELEMENT)
	private DialogSelectElement currencySelectElementMock;

	@Mock
	@Qualifier(AMOUNT_DIALOG_TEXT_ELEMENT)
	private DialogTextElement amountTextElementMock;

	@Mock
	private UsersSelectElementFactory usersSelectElementFactoryMock;

	@Mock
	private DialogBuilder dialogBuilderMock;

	@Mock
	private DialogSelectElement usersSelectElementMock;

	@Mock
	private Dialog dialogMock;

	@Before
	public void setUp() {
		when(usersSelectElementFactoryMock.createUsersSelectElementBasedOn(anyList()))
				.thenReturn(usersSelectElementMock);

		when(dialogBuilderMock.title(TRANSACTION_DIALOG_TITlE)).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.callbackId(TRANSACTION_DIALOG_CALLBACK_ID)).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock
				.elements(asList(amountTextElementMock, currencySelectElementMock, usersSelectElementMock)))
						.thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.submitLabel(CREATE_BUTTON_LABEL)).thenReturn(dialogBuilderMock);
		when(dialogBuilderMock.build()).thenReturn(dialogMock);
	}

	@Test
	public void testCreateTransactionsDialog() {
		transactionsDialogFactory.createTransactionsDialog(asList(DialogOption.builder().build()));

		verify(dialogBuilderMock).title(TRANSACTION_DIALOG_TITlE);
		verify(dialogBuilderMock).callbackId(TRANSACTION_DIALOG_CALLBACK_ID);
		verify(dialogBuilderMock)
				.elements(asList(amountTextElementMock, currencySelectElementMock, usersSelectElementMock));
		verify(dialogBuilderMock).submitLabel(CREATE_BUTTON_LABEL);
	}
}
