package io.slack.blockchain.processing.dialog;

import io.slack.blockchain.domain.dialog.contents.DialogContent;
import io.slack.blockchain.domain.processing.ProcessingResult;

public interface DialogProcessor<T extends DialogContent> {
	ProcessingResult process(final T dialogContent);
}
