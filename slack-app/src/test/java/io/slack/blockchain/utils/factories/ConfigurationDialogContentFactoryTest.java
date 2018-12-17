package io.slack.blockchain.utils.factories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.slack.blockchain.domain.dialog.contents.ConfigurationDialogContent;
import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.submissions.ConfigurationDialogSubmission;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationDialogContentFactoryTest {
	@InjectMocks
	private ConfigurationDialogContentFactory configurationDialogContentFactory;

	@Mock
	private DialogIdentityPayload dialogIdentityPayloadMock;

	@Mock
	private ConfigurationDialogSubmission configurationDialogSubmission;

	@Test
	public void testCreateDialogContent() {
		final ConfigurationDialogContent configurationDialogContent = configurationDialogContentFactory
				.createDialogContent(dialogIdentityPayloadMock, configurationDialogSubmission);

		assertThat(configurationDialogContent.getDialogIdentityPayload(), equalTo(dialogIdentityPayloadMock));
		assertThat(configurationDialogContent.getDialogSubmission(), equalTo(configurationDialogSubmission));
	}
}
