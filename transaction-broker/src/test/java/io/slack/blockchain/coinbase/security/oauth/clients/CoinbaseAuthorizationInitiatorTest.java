package io.slack.blockchain.coinbase.security.oauth.clients;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import io.slack.blockchain.coinbase.broker.clients.CoinbaseAuthorizationInitiator;
import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.broker.factories.DesktopFactory;
import io.slack.blockchain.coinbase.broker.utils.CoinbaseAuthorizationEndpointBuilderUtil;

@RunWith(MockitoJUnitRunner.class)
public class CoinbaseAuthorizationInitiatorTest {
	private static final String COINBASE_AUTHORIZATION_ENDPOINT = "coinbase/authorization/endpoint";

	@Autowired
	@InjectMocks
	private CoinbaseAuthorizationInitiator coinbaseAuthorizationInitiator;

	@Mock
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtilMock;

	@Mock
	private DesktopFactory desktopFactoryMock;

	@Mock
	private Desktop desktopMock;

	@Before
	public void setUp() {
		when(desktopFactoryMock.createDesktop()).thenReturn(desktopMock);
		when(coinbaseAuthorizationEndpointBuilderUtilMock.buildAuthorizationEndpoint(anyString()))
				.thenReturn(COINBASE_AUTHORIZATION_ENDPOINT);
	}

	@Test(expected = AuthorizationException.class)
	public void testInitiateAuthorizationBrowseFails() throws Exception {
		doThrow(IOException.class).when(desktopMock).browse(any(URI.class));

		coinbaseAuthorizationInitiator.initiateAuthorization();

		verify(desktopMock).browse(new URI(COINBASE_AUTHORIZATION_ENDPOINT));
	}

	@Test
	public void testInitiateAuthorization() throws Exception {
		coinbaseAuthorizationInitiator.initiateAuthorization();

		verify(desktopMock).browse(new URI(COINBASE_AUTHORIZATION_ENDPOINT));
	}
}
