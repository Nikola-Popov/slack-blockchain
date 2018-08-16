package io.transaction.broker;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.transaction.broker.security.CoinbaseStateManager;

@RestController(value = "/")
public class CoinbaseRestController {
	private static final String clientId = "fake";

	@Autowired
	private CoinbaseStateManager coinbaseStateManager;

	@GetMapping
	public ResponseEntity<Void> redirectAuthorization(final UriComponentsBuilder uriComponentsBuilder) {
		// TODO CONSIDER USING SOME SORT OF URL BUILDER HERE
		uriComponentsBuilder.scheme("https").host("www.coinbase.com/oauth/authorize")
				.queryParam("response_type", "code").queryParam("client_id", clientId)
				.queryParam("scope", "wallet:transactions:send").queryParam("meta[send_limit_amount]", 1)
				.queryParam("meta[send_limit_currency]", "USD").queryParam("meta[send_limit_period]", "day")
				.queryParam("state", coinbaseStateManager.generateRandomState()).build();
		String formattedUrl = String.format(url, coinbaseStateManager.generateRandomState());
		return status(MOVED_PERMANENTLY).header(LOCATION, formattedUrl).build();
	}

	@GetMapping
	public void authorize(@RequestParam("code") final String code, @RequestParam("state") final String state) {
		coinbaseStateManager.verify(state);

	}

}
