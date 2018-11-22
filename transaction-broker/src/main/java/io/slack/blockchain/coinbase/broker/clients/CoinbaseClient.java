package io.slack.blockchain.coinbase.broker.clients;

import static org.springframework.http.HttpStatus.OK;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.coinbase.broker.domain.AccountsResponse;
import io.slack.blockchain.coinbase.broker.domain.Transaction;
import io.slack.blockchain.coinbase.broker.exceptions.CoinbaseClientException;
import io.slack.blockchain.commons.http.HttpConstants;
import io.slack.blockchain.commons.http.RequestEntityFactory;
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

	public void send(final Transaction transaction) throws CoinbaseClientException {
		ResponseEntity<CoinbaseClientException> exchange = null;
		try {
			RequestEntity<Transaction> requestEntity = requestEntityFactory
					.createPostRequestEntity(constructEndpointURL(transaction.getRecipient()), transaction);
			requestEntity.getHeaders().add(HttpHeaders.AUTHORIZATION, HttpConstants.BEARER + "");
			// exchange = restTemplate.exchange(
			// null),
			// CoinbaseClientException.class);
		} catch (RestClientException | URISyntaxException e) {
			throw new CoinbaseClientException("");
		}

		exchange.getBody();
	}

	private String constructEndpointURL(final String recipient) {
		return String.format("https://api.coinbase.com/v2/accounts/%s/transactions", recipient);
	}
}
