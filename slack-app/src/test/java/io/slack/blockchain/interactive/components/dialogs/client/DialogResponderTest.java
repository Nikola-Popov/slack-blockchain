package io.slack.blockchain.interactive.components.dialogs.client;

import static org.hamcrest.CoreMatchers.equalTo;
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

import com.github.seratch.jslack.api.model.Attachment;
import com.github.seratch.jslack.api.model.Attachment.AttachmentBuilder;

import io.slack.blockchain.commons.AttachmentResponseFactory;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.domain.processing.ProcessingResult;

@RunWith(MockitoJUnitRunner.class)
public class DialogResponderTest {
	private static final String STATUS_COLOR = "statusColor";
	private static final String RESPONSE_MESSAGE = "responseMessage";
	private static final String RESPONSE_URL = "responseUrl";
	private Attachment ATTACHMENT = Attachment.builder().text(RESPONSE_MESSAGE).color(STATUS_COLOR).build();

	@InjectMocks
	private DialogResponder transactionDialogResponder;

	@Captor
	private ArgumentCaptor<Attachment> attachmentCaptor;

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

	@Mock
	private ProcessingResult processingResultMock;

	@Mock
	private AttachmentBuilder attachmentBuilderMock;

	@Before
	public void setUp() throws Exception {
		when(processingResultMock.getMessage()).thenReturn(RESPONSE_MESSAGE);
		when(processingResultMock.getStatusColor()).thenReturn(STATUS_COLOR);
		when(attachmentBuilderMock.text(RESPONSE_MESSAGE)).thenReturn(attachmentBuilderMock);
		when(attachmentBuilderMock.color(STATUS_COLOR)).thenReturn(attachmentBuilderMock);
		when(attachmentBuilderMock.build()).thenReturn(ATTACHMENT);
		when(attachmentResponseFactoryMock.createAttachmentResponse(attachmentCaptor.capture()))
				.thenReturn(attachmentResponseMock);
	}

	@Test(expected = URISyntaxException.class)
	public void testRespondCreatingPostReuqestEntityThrowsException() throws Exception {
		doThrow(URISyntaxException.class).when(requestEntityFactoryMock).createPostRequestEntity(RESPONSE_URL,
				attachmentResponseMock);

		transactionDialogResponder.respond(RESPONSE_URL, processingResultMock);
	}

	@Test
	public void testRespond() throws Exception {
		when(requestEntityFactoryMock.createPostRequestEntity(RESPONSE_URL, attachmentResponseMock))
				.thenReturn(requestEntityMock);

		transactionDialogResponder.respond(RESPONSE_URL, processingResultMock);

		assertThat(attachmentCaptor.getValue().getColor(), equalTo(STATUS_COLOR));
		assertThat(attachmentCaptor.getValue().getText(), equalTo(RESPONSE_MESSAGE));
		verify(restTemplateMock).exchange(requestEntityMock, Void.class);
	}
}
