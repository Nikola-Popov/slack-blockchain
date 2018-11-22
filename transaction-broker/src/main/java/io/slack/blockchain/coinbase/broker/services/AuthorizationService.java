package io.slack.blockchain.coinbase.broker.services;

import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;

public interface AuthorizationService {
	public String acquireAccessTokenConsumingCode(final String code) throws AuthorizationException;
}
