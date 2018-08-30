package io.slack.blockchain.interactive.components.dialogs.client;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.domain.attachments.Status;

@Component
public class DialogResponder {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AttachmentResponseFactory attachmentResponseFactory;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	public void respond(final String responseUrl, final Status status, final String responseMessage)
			throws URISyntaxException {
		final AttachmentResponse attachmentResponse = attachmentResponseFactory
				.createAttachmentResponse(Attachment.builder().text(responseMessage).status(status).build());
		final RequestEntity<AttachmentResponse> requestEntity = requestEntityFactory
				.createPostRequestEntity(responseUrl, attachmentResponse);
		restTemplate.exchange(requestEntity, Void.class);
	}
}
