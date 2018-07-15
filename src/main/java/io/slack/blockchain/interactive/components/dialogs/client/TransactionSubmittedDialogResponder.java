package io.slack.blockchain.interactive.components.dialogs.client;

import static io.slack.blockchain.domain.attachments.Status.GOOD;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;

@Component
public class TransactionSubmittedDialogResponder {
	private static final String SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE = "All good to go! You have sucessfuly created a transaction.";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AttachmentResponseFactory attachmentResponseFactory;

	public void respond(final String responseUrl) throws URISyntaxException {
		final AttachmentResponse attachmentResponse = attachmentResponseFactory.createAttachmentResponse(
				Attachment.builder().text(SUCESSFUL_TRANSACTION_RESPONSE_MESSAGE).status(GOOD).build());

		final RequestEntity<AttachmentResponse> requestEntity = RequestEntity.post(new URI(responseUrl))
				.contentType(APPLICATION_JSON_UTF8).body(attachmentResponse);
		restTemplate.exchange(requestEntity, Void.class);
	}
}
