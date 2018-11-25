package io.slack.blockchain.processing.dialog;

import org.springframework.stereotype.Component;

import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;
import io.slack.blockchain.domain.processing.ProcessingResult;

@Component
public class TransactionDialogProcessor implements DialogProcessor<TransactionDialogContent> {

	@Override
	public ProcessingResult process(final TransactionDialogContent transactionDialogContent) {
		// TODO Auto-generated method stub
		return null;
	}

}
