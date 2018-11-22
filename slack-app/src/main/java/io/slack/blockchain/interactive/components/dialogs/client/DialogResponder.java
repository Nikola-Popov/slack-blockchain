package io.slack.blockchain.interactive.components.dialogs.client;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.seratch.jslack.api.model.Attachment.AttachmentBuilder;

import io.slack.blockchain.commons.AttachmentResponseFactory;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.domain.processing.ProcessingResult;

@Component
public class DialogResponder {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AttachmentResponseFactory attachmentResponseFactory;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	@Autowired
	private AttachmentBuilder attachmentBuilder;

	public void respond(final String responseUrl, final ProcessingResult processingResult) throws URISyntaxException {
		final AttachmentResponse attachmentResponse = attachmentResponseFactory.createAttachmentResponse(
				attachmentBuilder.text(processingResult.getMessage()).color(processingResult.getStatusColor()).build());
		final RequestEntity<AttachmentResponse> requestEntity = requestEntityFactory
				.createPostRequestEntity(responseUrl, attachmentResponse);
		restTemplate.exchange(requestEntity, Void.class);
	}
}
