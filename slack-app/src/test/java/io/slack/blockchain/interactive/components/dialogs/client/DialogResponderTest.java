package io.slack.blockchain.interactive.components.dialogs.client;

import static io.slack.blockchain.domain.attachments.Status.GOOD;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;

@RunWith(MockitoJUnitRunner.class)
public class DialogResponderTest {
	private static final String RESPONSE_MESSAGE = "responseMessage";
	private static final String RESPONSE_URL = "responseUrl";
	private static final Attachment ATTACHMENT = Attachment.builder().text(RESPONSE_MESSAGE).status(GOOD).build();

	@InjectMocks
	private DialogResponder transactionDialogResponder;

	@Mock
	private RestTemplate restTemplateMock;

	@Mock
	private AttachmentResponseFactory attachmentResponseFactoryMock;

	@Mock
	private AttachmentResponse attachmentResponseMock;

	@Mock
	private RequestEntity<AttachmentResponse> requestEntityMock;

	@Mock
	private RequestEntityFactory requestEntityFactoryMock;

	@Captor
	private ArgumentCaptor<Attachment> attachmentCaptor;

	@Before
	public void setUp() throws Exception {
		when(attachmentResponseFactoryMock.createAttachmentResponse(attachmentCaptor.capture()))
				.thenReturn(attachmentResponseMock);
	}

	@Test(expected = URISyntaxException.class)
	public void testRespondCreatingPostReuqestEntityThrowsException() throws Exception {
		doThrow(URISyntaxException.class).when(requestEntityFactoryMock).createPostRequestEntity(RESPONSE_URL,
				attachmentResponseMock);

		transactionDialogResponder.respond(RESPONSE_URL, GOOD, RESPONSE_MESSAGE);
	}

	@Test
	public void testRespond() throws Exception {
		when(requestEntityFactoryMock.createPostRequestEntity(RESPONSE_URL, attachmentResponseMock))
				.thenReturn(requestEntityMock);

		transactionDialogResponder.respond(RESPONSE_URL, GOOD, RESPONSE_MESSAGE);

		assertThat(attachmentCaptor.getValue(), equalTo(ATTACHMENT));
		verify(restTemplateMock).exchange(requestEntityMock, Void.class);
	}
}
