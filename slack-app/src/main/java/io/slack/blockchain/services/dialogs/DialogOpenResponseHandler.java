package io.slack.blockchain.services.dialogs;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.methods.response.dialog.DialogOpenResponse;

import io.slack.blockchain.interactive.components.dialogs.exceptions.DialogOpenException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DialogOpenResponseHandler {
	private static final String ERROR_MESSAGE_PLACEHOLDER = "Dialog failed to open due to errors! Returned response: %s.";

	public void handleDialogOpenResponse(final DialogOpenResponse dialogOpenResponse) {
		if (!dialogOpenResponse.isOk()) {
			final String errorMessage = String.format(ERROR_MESSAGE_PLACEHOLDER, dialogOpenResponse.toString());
			log.error(errorMessage);
			throw new DialogOpenException(errorMessage);
		}
	}
}
