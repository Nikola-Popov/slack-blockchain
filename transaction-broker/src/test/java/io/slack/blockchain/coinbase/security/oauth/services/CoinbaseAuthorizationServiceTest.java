package io.slack.blockchain.coinbase.security.oauth.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.slack.blockchain.coinbase.broker.domain.CoinbaseOAuthResponse;
import io.slack.blockchain.coinbase.broker.exceptions.AuthorizationException;
import io.slack.blockchain.coinbase.broker.services.CoinbaseAuthorizationService;
import io.slack.blockchain.coinbase.broker.utils.CoinbaseAuthorizationEndpointBuilderUtil;
import io.slack.blockchain.commons.http.RequestEntityFactory;

@RunWith(MockitoJUnitRunner.class)
public class CoinbaseAuthorizationServiceTest {
	private static final String ACCESS_TOKEN = "accessToken";
	private static final String CODE = "code";
	private static final String COINBASE_ACESS_TOKEN_ENDPOINT = "coinbaseAcessTokenEndpoint";

	@InjectMocks
	@Autowired
	private CoinbaseAuthorizationService coinbaseAuthorizationService;

	@Mock
	private RestTemplate restTemplateMock;

	@Mock
	private RequestEntityFactory requestEntityFactoryMock;

	@Mock
	private CoinbaseAuthorizationEndpointBuilderUtil coinbaseAuthorizationEndpointBuilderUtilMock;

	@Mock
	private RequestEntity<Void> requestEntityMock;

	@Mock
	private ResponseEntity<CoinbaseOAuthResponse> responseEntityMock;

	@Mock
	private CoinbaseOAuthResponse coinbaseOAuthResponseMock;

	@Before
	public void setUp() throws Exception {
		when(coinbaseAuthorizationEndpointBuilderUtilMock.buildAccessTokenEndpoint(eq(CODE), anyString()))
				.thenReturn(COINBASE_ACESS_TOKEN_ENDPOINT);
		when(requestEntityFactoryMock.createPostRequestEntity(COINBASE_ACESS_TOKEN_ENDPOINT))
				.thenReturn(requestEntityMock);
		when(restTemplateMock.exchange(requestEntityMock, CoinbaseOAuthResponse.class)).thenReturn(responseEntityMock);
		when(responseEntityMock.getBody()).thenReturn(coinbaseOAuthResponseMock);
		when(coinbaseOAuthResponseMock.getAccessToken()).thenReturn(ACCESS_TOKEN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAcquireAccessTokenConsumingCodeWithNullCode() throws Exception {
		coinbaseAuthorizationService.acquireAccessTokenConsumingCode(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAcquireAccessTokenConsumingCodeWithEmptyStringCode() throws Exception {
		coinbaseAuthorizationService.acquireAccessTokenConsumingCode("");
	}

	@Test(expected = AuthorizationException.class)
	public void testAcquireAccessTokenConsumingCodePostExchangeFails() throws Exception {
		when(restTemplateMock.exchange(requestEntityMock, CoinbaseOAuthResponse.class))
				.thenThrow(RestClientException.class);

		coinbaseAuthorizationService.acquireAccessTokenConsumingCode(CODE);
	}

	@Test(expected = AuthorizationException.class)
	public void testAcquireAccessTokenConsumingCodeCreationOfPostRequestEntityFails() throws Exception {
		when(requestEntityFactoryMock.createPostRequestEntity(COINBASE_ACESS_TOKEN_ENDPOINT))
				.thenThrow(URISyntaxException.class);

		coinbaseAuthorizationService.acquireAccessTokenConsumingCode(CODE);
	}

	@Test
	public void testAcquireAccessTokenConsumingCode() throws Exception {
		assertThat(coinbaseAuthorizationService.acquireAccessTokenConsumingCode(CODE), equalTo(ACCESS_TOKEN));
	}
}
