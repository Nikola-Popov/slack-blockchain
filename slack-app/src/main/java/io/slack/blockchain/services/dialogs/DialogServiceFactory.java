package io.slack.blockchain.services.dialogs;

import org.springframework.stereotype.Component;

import io.slack.blockchain.services.dialogs.exceptions.DialogServiceInstantionException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DialogServiceFactory {
	private static final String DIALOG_SERVICE_INSTANTIATION_ERROR_MESSAGE_PLACEHOLDER = "Unable to instantiate [%s] class with triggerId [%s].";

	public <T extends DialogService> DialogService createDialogService(final String triggerId,
			final Class<T> classOfT) {
		try {
			return classOfT.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			final String errorMessage = String.format(DIALOG_SERVICE_INSTANTIATION_ERROR_MESSAGE_PLACEHOLDER,
					classOfT.getSimpleName(), triggerId);
			log.error(errorMessage, e);
			throw new DialogServiceInstantionException(errorMessage, e);
		}
	}
}
