package io.slack.blockchain.coinbase.broker.clients;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.broker.factories.DesktopFactory;
import io.slack.blockchain.coinbase.broker.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CoinbaseAuthorizationInitiator {
	@Autowired
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtil;

	@Autowired
	private DesktopFactory desktopFactory;

	public void initiateAuthorization() throws AuthorizationException {
		final Desktop desktop = desktopFactory.createDesktop();
		try {
			desktop.browse(new URI(coinbaseAuthorizationEndpointBuilderUtil.buildAuthorizationEndpoint("state")));
		} catch (IOException | URISyntaxException e) {
			final String errorMessage = "Unable to open web browser to initiate authorization";
			log.error(errorMessage, e);
			throw new AuthorizationException(errorMessage, e);
		}
	}
}
