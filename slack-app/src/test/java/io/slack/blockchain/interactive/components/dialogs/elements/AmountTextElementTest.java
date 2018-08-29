package io.slack.blockchain.interactive.components.dialogs.elements;

import static com.github.seratch.jslack.api.model.dialog.DialogSubType.NUMBER;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogTextElementConstants.AMOUNT_ELEMENT_PLACEHOLDER;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogTextElementConstants.AMOUNT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogTextElementConstants.TEXT_ELEMENT_MAX_ELEMENT_LENGTH;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmountTextElementTest {
	@Autowired
	private DialogTextElement amountTextElement;

	private static final DialogTextElement DIALOG_TEXT_ELEMENT = DialogTextElement.builder().label(AMOUNT_LABEL)
			.name(AMOUNT_LABEL.toLowerCase()).placeholder(AMOUNT_ELEMENT_PLACEHOLDER)
			.maxLength(TEXT_ELEMENT_MAX_ELEMENT_LENGTH).subtype(NUMBER).build();

	@Test
	public void testAmountSelectElementIsCorrectlyCreated() {
		assertThat(amountTextElement, equalTo(DIALOG_TEXT_ELEMENT));
	}
}
