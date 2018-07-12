package io.slack.blockchain.interactive.components.processors;

import com.google.gson.Gson;

import io.slack.blockchain.domain.TransactionDialogSubmission;

public class DialogProcessor {
	private final Gson gson;

	public DialogProcessor() {
		gson = new Gson();
	}

	public void processSubmittedData(final String payload) {
		TransactionDialogSubmission fromJson = gson.fromJson(payload, TransactionDialogSubmission.class);
		System.out.println(fromJson);
	}
}
