package io.slack.blockchain.interactive.components.dialogs.elements;

import static com.github.seratch.jslack.api.model.dialog.DialogSubType.NUMBER;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogTextElementConstants.AMOUNT_ELEMENT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogTextElementConstants.AMOUNT_LABEL;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.TransactionDialogTextElementConstants.TEXT_ELEMENT_MAX_ELEMENT_LENGTH;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@Component
public class AmountDialogTextElementBuilderImpl implements DialogElementBuilder {
	@Override
	public DialogElement build() {
		return DialogTextElement.builder().label(AMOUNT_LABEL).name(AMOUNT_LABEL.toLowerCase())
				.placeholder(AMOUNT_ELEMENT_LABEL).maxLength(TEXT_ELEMENT_MAX_ELEMENT_LENGTH).subtype(NUMBER).build();
	}
}
