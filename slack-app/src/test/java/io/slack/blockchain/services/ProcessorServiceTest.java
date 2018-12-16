package io.slack.blockchain.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;
import io.slack.blockchain.processing.dialog.DialogProcessor;
import io.slack.blockchain.services.processing.ProcessorService;
import io.slack.blockchain.services.processing.exceptions.ProcessingException;
import io.slack.blockchain.utils.factories.DialogContentFactory;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorServiceTest {
	private static final String INVALID_CALLBACK_ID = "invalidCallbackId";
	private static final String TRANSACTION_DIALOG_CALLBACK_ID = "transaction-dialog";
	private static final String CONFIGURATION_DIALOG_CALLBACK_ID = "configuration-dialog";
	private static final String RESPONSE_URL = "responseUrl";
	private static final String PAYLOAD = "payload";

	@InjectMocks
	private ProcessorService processorService;

	@Mock
	private DialogPayloadParser dialogPayloadParserMock;

	@Mock
	private DialogProcessor<ConfigurationDialogContent> configurationDialogProcessorMock;

	@Mock
	private DialogProcessor<TransactionDialogContent> transactionDialogProcessorMock;

	@Mock
	private Slack slackMock;

	@Mock
	private DialogIdentityPayload dialogIdentityPayloadMock;

	@Mock
	private Payload payloadMock;

	@Mock
	private TransactionDialogSubmission transactionDialogSubmissionMock;

	@Mock
	private ConfigurationDialogSubmission configurationDialogSubmissionMock;

	@Mock
	private DialogContentFactory<ConfigurationDialogSubmission> configurationDialogContentFactoryMock;

	@Mock
	private DialogContentFactory<TransactionDialogSubmission> transactionDialogContentFactoryMock;

	@Mock
	private TransactionDialogContent transactionDialogContentMock;

	@Mock
	private ConfigurationDialogContent configurationDialogContentMock;

	@Before
	public void setUp() {
		when(dialogPayloadParserMock.parseIdentity(PAYLOAD)).thenReturn(dialogIdentityPayloadMock);
		when(dialogIdentityPayloadMock.getResponseUrl()).thenReturn(RESPONSE_URL);
		when(dialogPayloadParserMock.parseSubmission(PAYLOAD, TransactionDialogSubmission.class))
				.thenReturn(transactionDialogSubmissionMock);
		when(dialogPayloadParserMock.parseSubmission(PAYLOAD, ConfigurationDialogSubmission.class))
				.thenReturn(configurationDialogSubmissionMock);
		when(transactionDialogContentFactoryMock.create(dialogIdentityPayloadMock, transactionDialogSubmissionMock))
				.thenReturn(transactionDialogContentMock);
		when(configurationDialogContentFactoryMock.create(dialogIdentityPayloadMock, configurationDialogSubmissionMock))
				.thenReturn(configurationDialogContentMock);
	}

	@Test(expected = ProcessingException.class)
	public void testProcessInvalidCallbackId() throws Exception {
		when(dialogIdentityPayloadMock.getCallbackId()).thenReturn(INVALID_CALLBACK_ID);

		processorService.process(PAYLOAD);

		verifyNoInteractionsWithTransactionDialog();
		verifyNoInteractionsWithConfigurationDialog();
	}

	@Test(expected = ProcessingException.class)
	public void testProcessFailsWhenTryingToSendResponseToSlack() throws Exception {
		doThrow(IOException.class).when(slackMock).send(RESPONSE_URL, payloadMock);

		processorService.process(PAYLOAD);
	}

	@Test
	public void testProcessTransactionDialog() throws Exception {
		when(dialogIdentityPayloadMock.getCallbackId()).thenReturn(TRANSACTION_DIALOG_CALLBACK_ID);

		processorService.process(PAYLOAD);

		verifyNoInteractionsWithConfigurationDialog();
	}

	@Test
	public void testProcessConfigurationDialog() throws Exception {
		when(dialogIdentityPayloadMock.getCallbackId()).thenReturn(CONFIGURATION_DIALOG_CALLBACK_ID);

		processorService.process(PAYLOAD);

		verifyNoInteractionsWithTransactionDialog();
	}

	private void verifyNoInteractionsWithTransactionDialog() {
		verify(dialogPayloadParserMock, never()).parseSubmission(PAYLOAD, TransactionDialogSubmission.class);
		verify(transactionDialogProcessorMock, never()).process(transactionDialogContentMock);
	}

	private void verifyNoInteractionsWithConfigurationDialog() {
		verify(dialogPayloadParserMock, never()).parseSubmission(PAYLOAD, ConfigurationDialogSubmission.class);
		verify(configurationDialogProcessorMock, never()).process(configurationDialogContentMock);
	}
}
