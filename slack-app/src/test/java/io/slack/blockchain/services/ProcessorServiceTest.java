package io.slack.blockchain.services;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.DialogIdentityPayload;
import io.slack.blockchain.domain.processing.ProcessingResult;
import io.slack.blockchain.interactive.components.dialogs.client.DialogResponder;
import io.slack.blockchain.processing.dialog.DialogProcessor;
import io.slack.blockchain.processing.dialog.DialogProcessorProvider;
import io.slack.blockchain.services.dialogs.exceptions.DialogResponderException;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorServiceTest {
	private static final String RESPONSE_URL = "responseUrl";

	@InjectMocks
	private ProcessorService processorService;

	@Mock
	private DialogResponder dialogResponderMock;

	@Mock
	private DialogProcessorProvider dialogProcessorProviderMock;

	@Mock
	private DialogContent<?> dialogContentMock;

	@Mock
	private DialogProcessor dialogProcessorMock;

	@Mock
	private ProcessingResult processingResultMock;

	@Mock
	private DialogIdentityPayload dialogIdentityPayloadMock;

	@Before
	public void setUp() {
		when(dialogProcessorProviderMock.provide(dialogContentMock)).thenReturn(dialogProcessorMock);
		when(dialogProcessorMock.process()).thenReturn(processingResultMock);
		when(dialogContentMock.getDialogIdentityPayload()).thenReturn(dialogIdentityPayloadMock);
		when(dialogIdentityPayloadMock.getResponseUrl()).thenReturn(RESPONSE_URL);
	}

	@Test(expected = DialogResponderException.class)
	public void testProcessDialogResponderRespondThrowsException() throws Exception {
		doThrow(DialogResponderException.class).when(dialogResponderMock).respond(RESPONSE_URL, processingResultMock);

		processorService.process(dialogContentMock);
	}

	@Test
	public void testProcess() throws Exception {
		processorService.process(dialogContentMock);

		verify(dialogResponderMock).respond(RESPONSE_URL, processingResultMock);
	}
}
