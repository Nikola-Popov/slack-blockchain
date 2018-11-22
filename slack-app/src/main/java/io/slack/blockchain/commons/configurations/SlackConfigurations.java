package io.slack.blockchain.commons.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest.UsersListRequestBuilder;

import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementFactory;
import io.slack.blockchain.interactive.components.dialogs.utils.CurrencyProvider;

@Configuration
public class SlackConfigurations {
	@Bean
	public Slack createSlack() {
		return Slack.getInstance();
	}

	@Bean
	public CurrencyProvider createCurrencyProvider() {
		return new CurrencyProvider();
	}

	@Bean
	public UsersSelectElementFactory createUsersSelectElementFactory() {
		return new UsersSelectElementFactory();
	}

	@Bean
	public UsersListRequestBuilder createUsersListRequestBuilder() {
		return UsersListRequest.builder();
	}

}
