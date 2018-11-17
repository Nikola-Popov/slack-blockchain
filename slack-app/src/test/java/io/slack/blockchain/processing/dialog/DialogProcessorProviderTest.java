package io.slack.blockchain.processing.dialog;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.utils.DialogContentUtil;
import io.slack.blockchain.utils.converters.dialog.DialogContentConverter;

@RunWith(MockitoJUnitRunner.class)
public class DialogProcessorProviderTest {
	@InjectMocks
	@Autowired
	private DialogProcessorProvider dialogProcessorProvider;

	@Mock(name = "transactionDialogContentConverter")
	private DialogContentConverter<TransactionDialogSubmission> transactionDialogContentConverterMock;

	@Mock(name = "configurationDialogContentConverter")
	private DialogContentConverter<ConfigurationDialogSubmission> configurationDialogContentConverterMock;

	@Mock
	private DialogContentUtil dialogContentUtilMock;

	@Mock
	private DialogContent<ConfigurationDialogSubmission> configurationDialogContentMock;

	@Mock
	private DialogContent<TransactionDialogSubmission> transactionDialogContentMock;

	@Before
	public void setUp() {
		when(dialogContentUtilMock.isDialogContentSubmissionAssignableFrom(transactionDialogContentMock,
				TransactionDialogSubmission.class)).thenReturn(false);
		when(dialogContentUtilMock.isDialogContentSubmissionAssignableFrom(configurationDialogContentMock,
				ConfigurationDialogSubmission.class)).thenReturn(false);

		when(configurationDialogContentConverterMock.convert(configurationDialogContentMock))
				.thenReturn(configurationDialogContentMock);
		when(transactionDialogContentConverterMock.convert(transactionDialogContentMock))
				.thenReturn(transactionDialogContentMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProvideInvalidDialogSubmission() {
		dialogProcessorProvider.provide(configurationDialogContentMock);
	}

	@Test
	public void testProvideTransactionDialogSubmissionProcessor() {
		when(dialogContentUtilMock.isDialogContentSubmissionAssignableFrom(transactionDialogContentMock,
				TransactionDialogSubmission.class)).thenReturn(true);

		assertThat(dialogProcessorProvider.provide(transactionDialogContentMock),
				instanceOf(TransactionDialogProcessor.class));
	}

	@Test
	public void testProvideConfigurationDialogSubmissionProcessor() {
		when(dialogContentUtilMock.isDialogContentSubmissionAssignableFrom(configurationDialogContentMock,
				ConfigurationDialogSubmission.class)).thenReturn(true);

		assertThat(dialogProcessorProvider.provide(configurationDialogContentMock),
				instanceOf(ConfigurationDialogProcessor.class));
	}
}
