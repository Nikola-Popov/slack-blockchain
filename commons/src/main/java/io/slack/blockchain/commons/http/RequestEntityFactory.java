package io.slack.blockchain.commons.http;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.RequestEntity.method;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.stereotype.Component;

@Component
public class RequestEntityFactory {
	public RequestEntity<Void> createPostRequestEntity(final String url) throws URISyntaxException {
		return createDefaultBodyBuilder(POST, url).build();
	}

	public <T> RequestEntity<T> createPostRequestEntity(final String url, final T body) throws URISyntaxException {
		return createDefaultBodyBuilder(POST, url).body(body);
	}

	private <T> BodyBuilder createDefaultBodyBuilder(final HttpMethod httpMethod, final String url)
			throws URISyntaxException {
		return method(httpMethod, new URI(url)).contentType(APPLICATION_JSON_UTF8);
	}
}
