package io.transaction.broker.coinbase.security.oauth.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Configuration
@PropertySource("classpath:coinbase.properties")
@ConfigurationProperties(prefix = "coinbase.oauth")
@Getter
public class CoinbaseConfigurationProperties {
	private String clientId;

	private String clientSecret;

	private String authorizationHost;

	private String tokenHost;

	private String defaultSendLimmit;
}
