// package io.slack.blockchain.processors;
//
// import static org.hamcrest.Matchers.equalTo;
// import static org.junit.Assert.assertThat;
// import static org.mockito.Mockito.when;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;
//
// import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
// import
// io.slack.blockchain.interactive.components.dialogs.parsers.TransactionSubmissionDialogParser;
//
// @RunWith(MockitoJUnitRunner.class)
// public class SubmittedTransactionProcessorTest {
// private static final String PAYLOAD = "payload";
//
// @InjectMocks
// private SubmittedTransactionProcessor submittedTransactionProcessor;
//
// @Mock
// private TransactionSubmissionDialogParser
// transactionSubmissionDialogParserMock;
//
// @Mock
// private TransactionDialogSubmission transactionDialogSubmissionMock;
//
// @Test
// public void testProcessSubmissionDialogData() throws Exception {
// when(transactionSubmissionDialogParserMock.parse(PAYLOAD))
// .thenReturn(transactionDialogSubmissionMock);
//
// assertThat(submittedTransactionProcessor.process(PAYLOAD),
// equalTo(transactionDialogSubmissionMock));
// }
// }
