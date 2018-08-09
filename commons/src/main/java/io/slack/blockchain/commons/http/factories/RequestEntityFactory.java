package io.slack.blockchain.commons.http.factories;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.RequestEntity.method;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

@Component
public class RequestEntityFactory {
	public <T> RequestEntity<T> createRequestEntiy(final HttpMethod httpMethod, final String url, final T body)
			throws URISyntaxException {
		return method(httpMethod, new URI(url)).contentType(APPLICATION_JSON_UTF8).body(body);
	}

	public <T> RequestEntity<T> createPostRequestEntity(final String url, final T body) throws URISyntaxException {
		return createRequestEntiy(POST, url, body);
	}
}
