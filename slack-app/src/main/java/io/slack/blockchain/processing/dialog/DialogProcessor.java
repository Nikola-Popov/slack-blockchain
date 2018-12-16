package io.slack.blockchain.processing.dialog;

import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.DialogContent;

public interface DialogProcessor<T extends DialogContent> {
	Payload process(final T dialogContent);
}
