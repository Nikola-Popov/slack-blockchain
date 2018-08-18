package io.transaction.broker.coinbase.security.oauth.utils;

import static io.slack.blockchain.commons.http.HttpConstants.HTTPS;
import static io.slack.blockchain.commons.miscellaneous.trading.Currency.USD;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.AUTHORIZATION_CODE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.CLIENT_ID;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.CLIENT_SECRET;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.CODE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.EXPIRE_EVERYDAY;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.GRANT_TYPE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.META_SEND_LIMIT_AMOUNT;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.META_SEND_LIMIT_CURRENCY;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.META_SEND_LIMIT_PERIOD;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.REDIRECT_URI;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.RESPONSE_TYPE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.SCOPE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.STATE;
import static io.transaction.broker.coinbase.security.oauth.constants.QueryParamsConstants.WALLET_TRANSACTIONS_SEND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import io.transaction.broker.coinbase.security.oauth.configurations.CoinbaseConfigurationProperties;

@Component
public class CoinbaseAuthorizationEndpointBuilderUtil {
	@Autowired
	private CoinbaseConfigurationProperties coinbaseConfigurationProperties;

	@Autowired
	private UriComponentsBuilder uriComponentsBuilder;

	public String buildAuthorizationEndpoint(final String state) {
		return uriComponentsBuilder.scheme(HTTPS).host(coinbaseConfigurationProperties.getAuthorizationHost())
				.queryParam(RESPONSE_TYPE, CODE).queryParam(CLIENT_ID, coinbaseConfigurationProperties.getClientId())
				.queryParam(SCOPE, WALLET_TRANSACTIONS_SEND)
				.queryParam(META_SEND_LIMIT_AMOUNT, coinbaseConfigurationProperties.getDefaultSendLimmit())
				.queryParam(META_SEND_LIMIT_CURRENCY, USD.name()).queryParam(META_SEND_LIMIT_PERIOD, EXPIRE_EVERYDAY)
				.queryParam(STATE, state).build().toUriString();
	}

	public String buildAccessTokenEndpoint(final String code, final String redirectUri) {
		return uriComponentsBuilder.scheme(HTTPS).host(coinbaseConfigurationProperties.getTokenHost())
				.queryParam(GRANT_TYPE, AUTHORIZATION_CODE).queryParam(CODE, code)
				.queryParam(CLIENT_ID, coinbaseConfigurationProperties.getClientId())
				.queryParam(CLIENT_SECRET, coinbaseConfigurationProperties.getClientSecret())
				.queryParam(REDIRECT_URI, redirectUri).build().toUriString();
	}
}
