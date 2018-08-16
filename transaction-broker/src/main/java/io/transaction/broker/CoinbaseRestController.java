package io.transaction.broker;

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

import io.slack.blockchain.commons.http.factories.RequestEntityFactory;
import io.transaction.broker.security.CoinbaseOauthAuthorizationResponse;
import io.transaction.broker.security.CoinbaseStateManager;

@RestController(value = "/")
public class CoinbaseRestController {
	private static final String DAY = "day";

	private static final String META_SEND_LIMIT_PERIOD = "meta[send_limit_period]";

	private static final String META_SEND_LIMIT_AMOUNT = "meta[send_limit_amount]";

	private static final String WALLET_TRANSACTIONS_SEND = "wallet:transactions:send";

	private static final String STATE = "state";

	private static final String META_SEND_LIMIT_CURRENCY = "meta[send_limit_currency]";

	private static final String SCOPE = "scope";

	private static final String COINBASE_COM_OAUTH_AUTHORIZE = "coinbase.com/oauth/authorize";

	private static final String RESPONSE_TYPE = "response_type";

	private static final String CLIENT_SECRET = "client_secret";

	private static final String API_COINBASE_COM_OAUTH_TOKEN = "api.coinbase.com/oauth/token";

	private static final String HTTPS = "https";

	private static final String REDIRECT_URI = "redirect_uri";

	private static final String CLIENT_ID = "client_id";

	private static final String GRANT_TYPE = "grant_type";

	private static final String AUTHORIZATION_CODE = "authorization_code";

	private static final String CODE2 = "code";

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
		String url = uriComponentsBuilder.scheme(HTTPS).host(COINBASE_COM_OAUTH_AUTHORIZE)
				.queryParam(RESPONSE_TYPE, CODE2).queryParam(CLIENT_ID, clientId)
				.queryParam(SCOPE, WALLET_TRANSACTIONS_SEND).queryParam(META_SEND_LIMIT_AMOUNT, 1)
				.queryParam(META_SEND_LIMIT_CURRENCY, "USD").queryParam(META_SEND_LIMIT_PERIOD, DAY)
				.queryParam(STATE, coinbaseStateManager.generateRandomState()).build().toUriString();
		String formattedUrl = String.format(url, coinbaseStateManager.generateRandomState());
		return status(MOVED_PERMANENTLY).header(LOCATION, formattedUrl).build();
	}

	public void acquireAccessToken(@RequestParam(CODE2) final String code, @RequestParam(STATE) final String state,
			final UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
		coinbaseStateManager.verify(state);
		String uriString = uriComponentsBuilder.scheme(HTTPS).host(API_COINBASE_COM_OAUTH_TOKEN)
				.queryParam(GRANT_TYPE, AUTHORIZATION_CODE).queryParam(CODE2, code).queryParam(CLIENT_ID, clientId)
				.queryParam(CLIENT_SECRET, clientSecret).queryParam(REDIRECT_URI, "redirect").build().toUriString();

		final ResponseEntity<CoinbaseOauthAuthorizationResponse> responseEntity = restTemplate.exchange(
				requestEntityFactory.createPostRequestEntity(uriString), CoinbaseOauthAuthorizationResponse.class);
	}
}
