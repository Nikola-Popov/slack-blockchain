package io.slack.blockchain.coinbase.security.oauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.stanford.ejalbert.BrowserLauncher;
import io.slack.blockchain.coinbase.security.oauth.utils.CoinbaseAuthorizationEndpointBuilderUtil;

@Component
public class CoinbaseAuthorizationInitiator {
	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	@Autowired
	private BrowserLauncher browserLauncher;

	public void initiateAuthorization() {
		browserLauncher.openURLinBrowser(coinbaseAuthorizationEndpointBuilderUtil.buildAuthorizationEndpoint("state"));
	}
}
