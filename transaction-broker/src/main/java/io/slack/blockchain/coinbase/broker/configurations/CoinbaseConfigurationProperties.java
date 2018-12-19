package io.slack.blockchain.coinbase.broker.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:coinbase.properties")
@ConfigurationProperties(prefix = "coinbase.oauth")
@Getter
@Setter
public class CoinbaseConfigurationProperties {
	private String clientId;

	private String clientSecret;

	private String authorizationHost;

	private String tokenHost;

	private String defaultSendLimmit;

	private String redirectUri;
}
