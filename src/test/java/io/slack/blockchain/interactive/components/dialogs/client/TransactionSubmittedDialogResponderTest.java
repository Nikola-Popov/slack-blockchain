package io.slack.blockchain.interactive.components.dialogs.client;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;

@RunWith(MockitoJUnitRunner.class)
public class TransactionSubmittedDialogResponderTest {
	private static final String RESPONSE_URL = "responseUrl";

	@InjectMocks
	@Spy
	private TransactionSubmittedDialogResponder transactionSubmittedDialogResponder;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private AttachmentResponseFactory attachmentResponseFactory;

	@Mock
	private Attachment attachment;

	@Mock
	private AttachmentResponse attachmentResponse;

	@Mock
	private RequestEntity<AttachmentResponse> requestEntityMock;

	@Test
	public void testRespond() throws Exception {
		when(attachmentResponseFactory.createAttachmentResponse(attachment)).thenReturn(attachmentResponse);

		doReturn(requestEntityMock).when(transactionSubmittedDialogResponder).buildRequestEntity(RESPONSE_URL,
				attachmentResponse);

		transactionSubmittedDialogResponder.respond(RESPONSE_URL);

		verify(restTemplate).exchange(requestEntityMock, Void.class);
	}
}
