package io.slack.blockchain.processing.dialog;

import io.slack.blockchain.domain.processing.ProcessingResult;

public interface DialogProcessor {
	ProcessingResult process();
}
