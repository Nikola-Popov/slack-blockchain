package io.slack.blockchain.coinbase.broker.services;

import static org.springframework.util.StringUtils.isEmpty;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.coinbase.broker.domain.CoinbaseOAuthResponse;
import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.broker.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoinbaseAuthorizationService implements AuthorizationService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	@Override
	public String acquireAccessTokenConsumingCode(final String code) throws AuthorizationException {
		if (isEmpty(code)) {
			throw new IllegalArgumentException("The provided code is empty!");
		}
		final String coinbaseAcessTokenEndpoint = coinbaseAuthorizationEndpointBuilderUtil
				.buildAccessTokenEndpoint(code, "http://9ac41555.ngrok.io/coinbase/authorization/granted");
		ResponseEntity<CoinbaseOAuthResponse> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(
					requestEntityFactory.createPostRequestEntity(coinbaseAcessTokenEndpoint),
					CoinbaseOAuthResponse.class);
		} catch (RestClientException | URISyntaxException exception) {
			final String errorMessage = "Coinbase OAuth service failed to authorize the request";
			log.error(errorMessage, exception);
			throw new AuthorizationException(errorMessage);
		}
		return responseEntity.getBody().getAccessToken();
	}
}
