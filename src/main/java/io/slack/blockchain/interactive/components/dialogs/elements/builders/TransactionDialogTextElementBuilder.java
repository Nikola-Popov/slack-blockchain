package io.slack.blockchain.interactive.components.dialogs.elements.builders;

import static com.github.seratch.jslack.api.model.dialog.DialogSubType.NUMBER;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.DialogElement;
import com.github.seratch.jslack.api.model.dialog.DialogTextElement;

@Component
public class TransactionDialogTextElementBuilder implements DialogElementBuilder {
	private static final String AMOUNT_ELEMENT_LABEL = "Amount of currency you wish to send";
	private static final String AMOUNT_LABEL = "Amount";
	private static final int TEXT_ELEMENT_MAX_ELEMENT_LENGTH = 100;

	@Override
	public DialogElement build() {
		return DialogTextElement.builder().label(AMOUNT_LABEL).name(AMOUNT_LABEL.toLowerCase())
				.placeholder(AMOUNT_ELEMENT_LABEL).maxLength(TEXT_ELEMENT_MAX_ELEMENT_LENGTH).subtype(NUMBER).build();
	}

}
