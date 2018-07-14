package io.slack.blockchain.interactive.components.dialogs;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;

import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DialogResponseValidator {
	private static final String OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE = "There was an error while trying to open a transaction dialog.";

	public void checkValidity(final DialogOpenResponse dialogOpenResponse) {
		if (!dialogOpenResponse.isOk()) {
			log.error(OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE);
			throw new DialogOpenException(OPENING_TRANSACTION_WINDOW_ERROR_MESSAGE);
		}
	}
}
