package io.slack.blockchain.coinbase.security.oauth.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.coinbase.security.oauth.utils.BrowserLauncher;
import io.slack.blockchain.coinbase.security.oauth.utils.CoinbaseAuthorizationEndpointBuilderUtil;

@Component
public class CoinbaseAuthorizationInitiator {
	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	public void initiateAuthorization() {
		new BrowserLauncher().openURL(coinbaseAuthorizationEndpointBuilderUtil.buildAuthorizationEndpoint("state"));
	}
}
