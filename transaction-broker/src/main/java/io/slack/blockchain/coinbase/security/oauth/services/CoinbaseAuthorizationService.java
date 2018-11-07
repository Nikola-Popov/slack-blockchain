package io.slack.blockchain.coinbase.security.oauth.services;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.coinbase.security.oauth.domain.OAuthResponse;
import io.slack.blockchain.coinbase.security.oauth.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.security.oauth.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.slack.blockchain.commons.services.GsonJsonService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoinbaseAuthorizationService implements AuthorizationService {
	@Autowired
	private GsonJsonService gsonJsonService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	private final String coinbaseAcessTokenEndpoint;

	public CoinbaseAuthorizationService(final String code) {
		coinbaseAcessTokenEndpoint = coinbaseAuthorizationEndpointBuilderUtil.buildAccessTokenEndpoint(code,
				"http://bb0b256d.ngrok.io/coinbase/authorization/granted");
	}

	@Override
	public String acquireAccessToken() throws AuthorizationException {
		ResponseEntity<OAuthResponse> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(
					requestEntityFactory.createPostRequestEntity(coinbaseAcessTokenEndpoint), OAuthResponse.class);
		} catch (RestClientException | URISyntaxException exception) {
			final String errorMessage = "Coinbase OAuth service failed to authorize the request. Returned response: "
					+ responseEntity;
			log.error(errorMessage, exception);
			throw new AuthorizationException(errorMessage);
		}

		return responseEntity.getBody().accessToken;
	}
}
