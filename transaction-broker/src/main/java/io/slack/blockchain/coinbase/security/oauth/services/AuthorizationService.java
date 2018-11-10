package io.slack.blockchain.coinbase.security.oauth.services;

import io.slack.blockchain.coinbase.security.oauth.exceptions.AuthorizationException;

public interface AuthorizationService {
	public String acquireAccessTokenConsumingCode(final String code) throws AuthorizationException;
}
