package io.slack.blockchain.commons.configurations.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class DefaultHttpConfigurations {
	@Bean
	public RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public UriComponentsBuilder createUriComponentsBuilder() {
		return UriComponentsBuilder.newInstance();
	}
}
