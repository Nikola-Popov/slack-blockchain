package io.slack.blockchain.coinbase.broker.utils;

import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.AUTHORIZATION_CODE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.CLIENT_ID;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.CLIENT_SECRET;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.CODE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.EXPIRE_EVERYDAY;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.GRANT_TYPE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.META_SEND_LIMIT_AMOUNT;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.META_SEND_LIMIT_CURRENCY;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.META_SEND_LIMIT_PERIOD;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.REDIRECT_URI;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.RESPONSE_TYPE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.SCOPE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.STATE;
import static io.slack.blockchain.coinbase.broker.constants.QueryParamsConstants.WALLET_TRANSACTIONS_SEND;
import static io.slack.blockchain.commons.http.HttpConstants.HTTPS_SCHEME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import io.slack.blockchain.coinbase.broker.configurations.CoinbaseConfigurationProperties;

@Component
public class CoinbaseAuthorizationEndpointBuilderUtil {
	private static final String DEFAULT_CURRENCY = "USD";

	@Autowired
	private CoinbaseConfigurationProperties coinbaseConfigurationProperties;

	@Autowired
	private UriComponentsBuilder uriComponentsBuilder;

	public String buildAuthorizationEndpoint(final String state) {
		return uriComponentsBuilder.scheme(HTTPS_SCHEME).host(coinbaseConfigurationProperties.getAuthorizationHost())
				.queryParam(RESPONSE_TYPE, CODE).queryParam(CLIENT_ID, coinbaseConfigurationProperties.getClientId())
				.queryParam(SCOPE, WALLET_TRANSACTIONS_SEND)
				.queryParam(META_SEND_LIMIT_AMOUNT, coinbaseConfigurationProperties.getDefaultSendLimmit())
				.queryParam(META_SEND_LIMIT_CURRENCY, DEFAULT_CURRENCY)
				.queryParam(META_SEND_LIMIT_PERIOD, EXPIRE_EVERYDAY).queryParam(STATE, state).build().toUriString();
	}

	public String buildAccessTokenEndpoint(final String code) {
		return uriComponentsBuilder.scheme(HTTPS_SCHEME).host(coinbaseConfigurationProperties.getTokenHost())
				.queryParam(GRANT_TYPE, AUTHORIZATION_CODE).queryParam(CODE, code)
				.queryParam(CLIENT_ID, coinbaseConfigurationProperties.getClientId())
				.queryParam(CLIENT_SECRET, coinbaseConfigurationProperties.getClientSecret())
				.queryParam(REDIRECT_URI, coinbaseConfigurationProperties.getRedirectUri()).build().toUriString();
	}
}
