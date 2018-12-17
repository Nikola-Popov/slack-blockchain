package io.slack.blockchain.processing.dialog;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.DialogContent;
import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;

@Component
public class TransactionDialogProcessor implements DialogProcessor<TransactionDialogSubmission> {

	@Override
	public Payload process(DialogContent<TransactionDialogSubmission> dialogContent) {
		// TODO Auto-generated method stub
		return null;
	}

}
