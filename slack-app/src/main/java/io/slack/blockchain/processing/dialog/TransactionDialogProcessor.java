package io.slack.blockchain.processing.dialog;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;

@Component
public class TransactionDialogProcessor implements DialogProcessor<TransactionDialogContent> {

	@Override
	public Payload process(final TransactionDialogContent transactionDialogContent) {
		// TODO Auto-generated method stub
		return null;
	}

}
