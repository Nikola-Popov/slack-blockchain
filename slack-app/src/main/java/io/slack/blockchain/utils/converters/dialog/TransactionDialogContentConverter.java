package io.slack.blockchain.utils.converters.dialog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@Component
public class TransactionDialogContentConverter implements DialogContentConverter<TransactionDialogSubmission> {
	@Autowired
	private DialogSubmissionConverter dialogSubmissionConverter;

	@Override
	public <S> DialogContent<TransactionDialogSubmission> convert(final DialogContent<S> dialogContent) {
		return new DialogContent<TransactionDialogSubmission>(dialogContent.getDialogIdentityPayload(),
				dialogSubmissionConverter.convertToTransactionDialogSubmssion(dialogContent.getSubmission()));
	}
}
