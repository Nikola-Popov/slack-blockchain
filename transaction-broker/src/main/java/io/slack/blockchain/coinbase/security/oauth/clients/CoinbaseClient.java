package io.slack.blockchain.coinbase.security.oauth.clients;

import static org.springframework.http.HttpStatus.OK;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.coinbase.security.oauth.domain.AccountsResponse;
import io.slack.blockchain.coinbase.security.oauth.exceptions.CoinbaseClientException;
import io.slack.blockchain.commons.http.RequestEntityFactory;
import io.slack.blockchain.commons.miscellaneous.trading.Currency;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoinbaseClient {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestEntityFactory requestEntityFactory;

	public AccountsResponse listAccounts() throws CoinbaseClientException {
		ResponseEntity<AccountsResponse> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(
					requestEntityFactory.createGetRequestEntity("https://api.coinbase.com/v2/accounts"),
					AccountsResponse.class);
		} catch (RestClientException | URISyntaxException e) {
			log.error("");
			throw new CoinbaseClientException("", e);
		}

		if (responseEntity.getStatusCode() != OK) {
			throw new CoinbaseClientException("Unable to retrieve users accounts!");
		}

		return responseEntity.getBody();
	}

	public void sendTransaction(final Currency currency, final Double amount, final String recipient)
			throws CoinbaseClientException {
		ResponseEntity<CoinbaseClientException> exchange = null;
		try {
			exchange = restTemplate.exchange(
					requestEntityFactory.createPostRequestEntity(
							String.format("https://api.coinbase.com/v2/accounts/%s/transactions", recipient), null),
					CoinbaseClientException.class);
		} catch (RestClientException | URISyntaxException e) {
			throw new CoinbaseClientException("");
		}

		exchange.getBody();
	}
}
