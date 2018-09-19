// package io.slack.blockchain.interactive.components.dialogs.client;
//
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.springframework.http.RequestEntity;
// import org.springframework.web.client.RestTemplate;
//
// import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
// import io.slack.blockchain.commons.http.RequestEntityFactory;
// import io.slack.blockchain.domain.attachments.Attachment;
// import io.slack.blockchain.domain.attachments.AttachmentResponse;
//
// @RunWith(MockitoJUnitRunner.class)
// public class DialogResponderTest {
// private static final String RESPONSE_URL = "responseUrl";
//
// @InjectMocks
// private DialogResponder transactionDialogResponder;
//
// @Mock
// private RestTemplate restTemplateMock;
//
// @Mock
// private AttachmentResponseFactory attachmentResponseFactoryMock;
//
// @Mock
// private AttachmentResponse attachmentResponseMock;
//
// @Mock
// private RequestEntity<AttachmentResponse> requestEntityMock;
//
// @Mock
// private RequestEntityFactory requestEntityFactoryMock;
//
// @Test
// public void testRespond() throws Exception {
// when(attachmentResponseFactoryMock.createAttachmentResponse(any(Attachment.class)))
// .thenReturn(attachmentResponseMock);
// when(requestEntityFactoryMock.createPostRequestEntity(RESPONSE_URL,
// attachmentResponseMock))
// .thenReturn(requestEntityMock);
//
// transactionDialogResponder.respond(RESPONSE_URL);
//
// verify(restTemplateMock).exchange(requestEntityMock, Void.class);
// }
// }
