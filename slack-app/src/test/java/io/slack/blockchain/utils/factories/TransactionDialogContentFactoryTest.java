package io.slack.blockchain.utils.factories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.slack.blockchain.domain.dialog.contents.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.contents.TransactionDialogContent;
import io.slack.blockchain.domain.dialog.submissions.TransactionDialogSubmission;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDialogContentFactoryTest {
	@InjectMocks
	private TransactionDialogContentFactory transactionDialogContentFactory;

	@Mock
	private DialogIdentityPayload dialogIdentityPayloadMock;

	@Mock
	private TransactionDialogSubmission transactionDialogSubmissionMock;

	@Test
	public void testCreateDialogContent() {
		final TransactionDialogContent transactionDialogContent = transactionDialogContentFactory
				.createDialogContent(dialogIdentityPayloadMock, transactionDialogSubmissionMock);

		assertThat(transactionDialogContent.getDialogIdentityPayload(), equalTo(dialogIdentityPayloadMock));
		assertThat(transactionDialogContent.getDialogSubmission(), equalTo(transactionDialogSubmissionMock));
	}
}
