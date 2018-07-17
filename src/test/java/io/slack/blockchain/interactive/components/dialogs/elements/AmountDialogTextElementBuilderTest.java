package io.slack.blockchain.interactive.components.dialogs.elements;

import static com.github.seratch.jslack.api.model.dialog.DialogSubType.NUMBER;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogTextElementConstants.AMOUNT_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogTextElementConstants.AMOUNT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogTextElementConstants.TEXT_ELEMENT_MAX_ELEMENT_LENGTH;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

public class AmountDialogTextElementBuilderTest {
	@Autowired
	private AmountDialogTextElementBuilder amountDialogTextElementBuilder;

	private static final DialogTextElement DIALOG_TEXT_ELEMENT = DialogTextElement.builder().label(AMOUNT_LABEL)
			.name(AMOUNT_LABEL.toLowerCase()).placeholder(AMOUNT_ELEMENT_LABEL)
			.maxLength(TEXT_ELEMENT_MAX_ELEMENT_LENGTH).subtype(NUMBER).build();

	@Test
	public void testBuild() {
		assertThat(amountDialogTextElementBuilder.build(), equalTo(DIALOG_TEXT_ELEMENT));
	}
}
