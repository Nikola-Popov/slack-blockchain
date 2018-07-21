package io.slack.blockchain.services;

import static io.slack.blockchain.domain.attachments.Status.GOOD;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.domain.SlackUser;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.repositories.SlackUsersRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	private static final Attachment ATTACHMENT = Attachment.builder().text("text").status(GOOD).build();
	private static final AttachmentResponse ATTACHMENT_RESPONSE = new AttachmentResponse(ATTACHMENT);

	@InjectMocks
	private UserService userService;

	@Mock
	private SlackUsersRepository slackUsersRepositoryMock;

	@Mock
	private AttachmentResponseFactory attachmentResponseFactoryMock;

	@Test
	public void testProcess() {
		when(attachmentResponseFactoryMock.createAttachmentResponse(any(Attachment.class))).thenReturn(ATTACHMENT_RESPONSE);

		assertThat(userService.process(any(SlackUser.class)), equalTo(ATTACHMENT_RESPONSE));
	}
}
