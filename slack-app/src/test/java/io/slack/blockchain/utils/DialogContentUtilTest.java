package io.slack.blockchain.utils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogContent;
import io.slack.blockchain.domain.dialog.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;

@RunWith(MockitoJUnitRunner.class)
public class DialogContentUtilTest {
	@Autowired
	@InjectMocks
	private DialogContentUtil dialogContentUtil;

	private DialogContent<TransactionDialogSubmission> dialogContentMock;

	@Before
	public void setUp() {
		dialogContentMock = new DialogContent<>(new DialogIdentityPayload(), new TransactionDialogSubmission());
	}

	@Test
	public void testIsDialogContentSubmissionAssignableFrom() {
		assertThat(dialogContentUtil.isDialogContentSubmissionAssignableFrom(dialogContentMock,
				TransactionDialogSubmission.class), equalTo(true));
	}

	@Test
	public void testIsDialogContentSubmissionAssignableFromFailsBecaseOfDiffence() {
		assertThat(dialogContentUtil.isDialogContentSubmissionAssignableFrom(dialogContentMock,
				ConfigurationDialogSubmission.class), equalTo(false));
	}
}
