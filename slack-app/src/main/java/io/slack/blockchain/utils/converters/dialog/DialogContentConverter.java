package io.slack.blockchain.utils.converters.dialog;

import io.slack.blockchain.domain.dialog.DialogContent;

public interface DialogContentConverter<R> {
	<S> DialogContent<R> convert(final DialogContent<S> dialogContent);
}
