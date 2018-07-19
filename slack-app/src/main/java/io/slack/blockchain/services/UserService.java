package io.slack.blockchain.services;

import static io.slack.blockchain.domain.attachments.Status.GOOD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.commons.factories.AttachmentResponseFactory;
import io.slack.blockchain.domain.SlackUser;
import io.slack.blockchain.domain.attachments.Attachment;
import io.slack.blockchain.domain.attachments.AttachmentResponse;
import io.slack.blockchain.repositories.SlackUsersRepository;

@Service
public class UserService {
	private static final String ADDRESS_CONFIGURED_RESPONSE_MESSAGE = "Bitcoin address configured successfuly!\nYou are now able to make transactions.";

	@Autowired
	private SlackUsersRepository slackUsersRepository;

	@Autowired
	private AttachmentResponseFactory attachmentResponseFactory;

	public AttachmentResponse process(final SlackUser slackUser) {
		slackUsersRepository.save(slackUser);
		return attachmentResponseFactory.createAttachmentResponse(
				Attachment.builder().text(ADDRESS_CONFIGURED_RESPONSE_MESSAGE).status(GOOD).build());
	}
}
