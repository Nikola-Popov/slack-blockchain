package io.slack.blockchain.commons.configurations.slack;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:slack.properties")
@ConfigurationProperties(prefix = "slack")
@Getter
@Setter
public class SlackConfigurationProperties {
	@NotEmpty
	private String oauthToken;
}