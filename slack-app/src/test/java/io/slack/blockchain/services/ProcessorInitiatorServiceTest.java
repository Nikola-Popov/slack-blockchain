package io.slack.blockchain.services;

import static io.slack.blockchain.interactive.components.dialogs.elements.constants.configuration.ConfigurationDialogConstants.CONFIGURATION_DIALOG_CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.elements.constants.transaction.TransactionDialogConstants.TRANSACTION_DIALOG_CALLBACK_ID;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.interactive.components.dialogs.parsers.DialogPayloadParser;
import io.slack.blockchain.processing.dialog.providers.DialogContentProvider;
import io.slack.blockchain.services.dialogs.exceptions.MissingDialogSubmissionException;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorInitiatorServiceTest {
	private static final String MISSING_CALLBACK_ID = "missing-callback-id";
	private static final String PAYLOAD = "payload";

	@InjectMocks
	private ProcessorInitiatorService processorInitiatorService;

	@Mock
	private DialogPayloadParser dialogPayloadParserMock;

	@Mock
	private ProcessorService processorServiceMock;

	@Mock
	private DialogContentProvider dialogContentProviderMock;

	@Mock
	private DialogIdentityPayload dialogIdentityPayloadMock;

	@Mock
	private DialogContent<TransactionDialogSubmission> transactionDialogContentMock;

	@Mock
	private DialogContent<ConfigurationDialogSubmission> configurationDialogContentMock;

	@Before
	public void setUp() {
		when(dialogPayloadParserMock.parseIdentity(PAYLOAD)).thenReturn(dialogIdentityPayloadMock);
		when(dialogContentProviderMock.provide(PAYLOAD, TransactionDialogSubmission.class))
				.thenReturn(transactionDialogContentMock);
		when(dialogContentProviderMock.provide(PAYLOAD, ConfigurationDialogSubmission.class))
				.thenReturn(configurationDialogContentMock);
	}

	@Test(expected = MissingDialogSubmissionException.class)
	public void testInitiateProcessingWithMissingDialogSubmission() {
		when(dialogIdentityPayloadMock.getCallbackId()).thenReturn(MISSING_CALLBACK_ID);

		processorInitiatorService.initiateProcessing(PAYLOAD);
	}

	@Test
	public void testInitiateProcessingTransactionDialog() {
		when(dialogIdentityPayloadMock.getCallbackId()).thenReturn(TRANSACTION_DIALOG_CALLBACK_ID);

		processorInitiatorService.initiateProcessing(PAYLOAD);

		verify(processorServiceMock).process(transactionDialogContentMock);
	}

	@Test
	public void testInitiateProcessingConfigurationDialog() {
		when(dialogIdentityPayloadMock.getCallbackId()).thenReturn(CONFIGURATION_DIALOG_CALLBACK_ID);

		processorInitiatorService.initiateProcessing(PAYLOAD);

		verify(processorServiceMock).process(configurationDialogContentMock);
	}
}
