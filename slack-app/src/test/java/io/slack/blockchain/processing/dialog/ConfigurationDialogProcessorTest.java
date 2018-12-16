package io.slack.blockchain.processing.dialog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.seratch.jslack.api.webhook.Payload;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.persitence.User;
import io.slack.blockchain.processing.payload.ResponsePayloadBuilder;
import io.slack.blockchain.repositories.UserRepository;
import io.slack.blockchain.utils.converters.UserConverter;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationDialogProcessorTest {
	private static final Payload PAYLOAD = Payload.builder().text("payload-text").build();

	@InjectMocks
	private ConfigurationDialogProcessor configurationDialogProcessor;

	@Mock
	private UserRepository userRepositoryMock;

	@Mock
	private UserConverter userConverterMock;

	@Mock
	private ResponsePayloadBuilder configurationDialogResponsePayloadBuilderMock;

	@Mock
	private ConfigurationDialogContent configurationDialogContentMock;

	@Mock
	private User userMock;

	@Before
	public void setUp() {
		when(userConverterMock.convert(configurationDialogContentMock)).thenReturn(userMock);
		when(userRepositoryMock.save(userMock)).thenReturn(userMock);
		when(configurationDialogResponsePayloadBuilderMock.buildResponsePayload()).thenReturn(PAYLOAD);
	}

	@Test
	public void testProcess() {
		assertThat(configurationDialogProcessor.process(configurationDialogContentMock), equalTo(PAYLOAD));
	}
}
