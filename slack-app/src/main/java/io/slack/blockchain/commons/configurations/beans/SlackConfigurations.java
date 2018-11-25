package io.slack.blockchain.commons.configurations.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest;
import com.github.seratch.jslack.api.methods.request.users.UsersListRequest.UsersListRequestBuilder;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.Payload.PayloadBuilder;

import io.slack.blockchain.interactive.components.dialogs.elements.UsersSelectElementFactory;
import io.slack.blockchain.interactive.components.dialogs.utils.CurrencyProvider;

@Configuration
public class SlackConfigurations {
	@Bean
	public Slack createSlack() {
		return Slack.getInstance();
	}

	@Bean
	public PayloadBuilder createPayloadBuilder() {
		return Payload.builder();
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
