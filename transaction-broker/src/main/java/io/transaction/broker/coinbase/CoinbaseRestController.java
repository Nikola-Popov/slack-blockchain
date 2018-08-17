package io.transaction.broker.coinbase;

import static io.slack.blockchain.commons.http.HttpConstants.HTTPS;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.USD;
import static io.transaction.broker.coinbase.security.oauth.constants.CoinbaseOAuthEndpoints.AUTHORIZATION_HOST;
import static io.transaction.broker.coinbase.security.oauth.constants.CoinbaseOAuthEndpoints.TOKEN_HOST;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.AUTHORIZATION_CODE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.CLIENT_ID;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.CLIENT_SECRET;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.EXPIRE_EVERYDAY;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.GRANT_TYPE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.META_SEND_LIMIT_AMOUNT;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.META_SEND_LIMIT_CURRENCY;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.META_SEND_LIMIT_PERIOD;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.REDIRECT_URI;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.RESPONSE_TYPE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.WALLET_TRANSACTIONS_SEND;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.ResponseEntity.status;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.transaction.broker.coinbase.security.oauth.domain.OAuthResponse;
import io.transaction.broker.coinbase.security.oauth.utils.CoinbaseStateManager;

@RestController(value = "/")
public class CoinbaseRestController {

	private static final int DEFAULT_SEND_LIMMIT_AMOUNT = 1;
	private static final String SCOPE = "scope";
	private static final String STATE = "state";

	private static final String CODE = "code";

	private static final String clientId = "fake";

	@Autowired
	private CoinbaseStateManager coinbaseStateManager;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	String clientSecret;

	@GetMapping
	public ResponseEntity<Void> redirectAuthorizationRequest(final UriComponentsBuilder uriComponentsBuilder) {
		String url = uriComponentsBuilder.scheme(HTTPS).host(AUTHORIZATION_HOST).queryParam(RESPONSE_TYPE, CODE)
				.queryParam(CLIENT_ID, clientId).queryParam(SCOPE, WALLET_TRANSACTIONS_SEND)
				.queryParam(META_SEND_LIMIT_AMOUNT, DEFAULT_SEND_LIMMIT_AMOUNT)
				.queryParam(META_SEND_LIMIT_CURRENCY, USD.name()).queryParam(META_SEND_LIMIT_PERIOD, EXPIRE_EVERYDAY)
				.queryParam(STATE, coinbaseStateManager.generateRandomState()).build().toUriString();
		String formattedUrl = String.format(url, coinbaseStateManager.generateRandomState());
		return status(MOVED_PERMANENTLY).header(LOCATION, formattedUrl).build();
	}

	public void acquireAccessToken(@RequestParam(CODE) final String code, @RequestParam(STATE) final String state,
			final UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
		coinbaseStateManager.verify(state);
		String uriString = uriComponentsBuilder.scheme(HTTPS).host(TOKEN_HOST)
				.queryParam(GRANT_TYPE, AUTHORIZATION_CODE).queryParam(CODE, code).queryParam(CLIENT_ID, clientId)
				.queryParam(CLIENT_SECRET, clientSecret).queryParam(REDIRECT_URI, "redirect").build().toUriString();

		final ResponseEntity<OAuthResponse> responseEntity = restTemplate
				.exchange(requestEntityFactory.createPostRequestEntity(uriString), OAuthResponse.class);
	}
}
