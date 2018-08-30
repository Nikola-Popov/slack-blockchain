package io.slack.blockchain.processing;

import io.slack.blockchain.domain.processing.ProcessingResult;

public interface DialogProcessor {
	ProcessingResult process();
}
