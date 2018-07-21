package io.slack.blockchain.processors;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.client.TransactionSubmittedDialogResponder;
import io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;

@RunWith(MockitoJUnitRunner.class)
public class SubmittedTransactionProcessorTest {
	private static final String RESPONSE_URL = "response-url";
	private static final String PAYLOAD = "payload";

	@InjectMocks
	private SubmittedTransactionProcessor submittedTransactionProcessor;

	@Mock
	private TransactionSubmissionDialogParser transactionSubmissionDialogParserMock;

	@Mock
	private TransactionSubmittedDialogResponder transactionSubmittedDialogResponderMock;

	@Mock
	private TransactionDialogSubmission transactionDialogSubmissionMock;

	@Test
	public void testProcessSubmissionDialogData() throws Exception {
		when(transactionSubmissionDialogParserMock.parseSubmittedData(PAYLOAD))
				.thenReturn(transactionDialogSubmissionMock);
		when(transactionDialogSubmissionMock.getResponseUrl()).thenReturn(RESPONSE_URL);

		submittedTransactionProcessor.processSubmissionDialogData(PAYLOAD);

		verify(transactionDialogSubmissionMock).getResponseUrl();
		verify(transactionSubmittedDialogResponderMock).respond(RESPONSE_URL);
	}
}
