package io.slack.blockchain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.SlackUser;
import io.slack.blockchain.repositories.SlackUsersRepository;

@Service
public class UserService {
	private static final String SUCESSFUL_SUBMMITED_TRANSACTION_MESSAGE = "All good to go! Your transaction was submitted succesfuly.";
	private static final String ADDRESS_CONFIGURED_RESPONSE_MESSAGE = "Bitcoin address configured successfuly!\nYou are now able to make transactions.";

	@Autowired
	private SlackUsersRepository slackUsersRepository;

	public void process(final SlackUser slackUser) {
		slackUsersRepository.save(slackUser);
	}
}
