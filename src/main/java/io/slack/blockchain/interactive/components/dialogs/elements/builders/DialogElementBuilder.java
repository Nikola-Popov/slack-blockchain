package io.slack.blockchain.interactive.components.dialogs.elements.builders;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.model.dialog.DialogElement;

@Component
public interface DialogElementBuilder {
	DialogElement build();
}
