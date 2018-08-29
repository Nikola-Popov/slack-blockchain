package io.slack.blockchain.interactive.components.dialogs.parsers;

import io.slack.blockchain.domain.dialog.DialogSubmission;

public interface DialogSumissionParser {
	public DialogSubmission parse(final String payload);
}
