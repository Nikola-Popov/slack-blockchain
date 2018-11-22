package io.slack.blockchain.coinbase.security.oauth.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import io.slack.blockchain.coinbase.broker.controllers.CoinbaseAuthorizationHandlerRestController;
import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.broker.services.AuthorizationService;

@RunWith(MockitoJUnitRunner.class)
public class CoinbaseAuthorizationHandlerRestControllerTest {
	private static final String ACCESS_TOKEN = "accessToken";
	private static final String CODE = "code";
	private static final String STATE = "state";

	@Autowired
	@InjectMocks
	private CoinbaseAuthorizationHandlerRestController coinbaseAuthorizationHandlerRestController;

	@Mock
	private AuthorizationService authorizationServiceMock;

	@Test(expected = AuthorizationException.class)
	public void testAuthorizeAcquiringAccessTokenFails() throws Exception {
		when(authorizationServiceMock.acquireAccessTokenConsumingCode(CODE)).thenThrow(AuthorizationException.class);

		coinbaseAuthorizationHandlerRestController.authorize(CODE, STATE);
	}

	@Test
	public void testAuthorize() throws Exception {
		when(authorizationServiceMock.acquireAccessTokenConsumingCode(CODE)).thenReturn(ACCESS_TOKEN);

		coinbaseAuthorizationHandlerRestController.authorize(CODE, STATE);

		verify(authorizationServiceMock).acquireAccessTokenConsumingCode(CODE);
	}
}
