package io.slack.blockchain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.slack.blockchain.domain.SlackUser;
import io.slack.blockchain.repositories.SlackUsersRepository;

@Service
public class UserService {
	@Autowired
	private SlackUsersRepository slackUsersRepository;

	public void process(final SlackUser slackUser) {
		slackUsersRepository.save(slackUser);
		System.out.println(slackUsersRepository.findAll());
	}
}
