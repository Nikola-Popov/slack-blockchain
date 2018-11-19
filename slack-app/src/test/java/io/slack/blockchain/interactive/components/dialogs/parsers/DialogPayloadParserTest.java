package io.slack.blockchain.interactive.components.dialogs.parsers;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.util.ResourceUtils.getFile;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.slack.blockchain.commons.miscellaneous.trading.Currency;
import io.slack.blockchain.domain.dialog.ConfigurationDialogSubmission;
import io.slack.blockchain.domain.dialog.DialogIdentityPayload;
import io.slack.blockchain.domain.dialog.TransactionDialogSubmission;
import io.slack.blockchain.domain.users.SlackUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DialogPayloadParserTest {
	private static final String DIALOGS_RESOURCE_FOLDER_CLASSPATH = "classpath:payload_parser/";
	private static final DialogIdentityPayload DIALOG_IDENTITY_PAYLOAD = new DialogIdentityPayload(
			new SlackUser.User("username", "userId"), new SlackUser.Team("teamId"), "callbackId",
			"https://hooks.slack.com/app/T012AB0A1/123456789/JpmK0yzoZDeRiqfeduTBYXWQ");
	private static final TransactionDialogSubmission TRANSACTION_DIALOG_SUBMISSION = new TransactionDialogSubmission(10,
			Currency.BITCOIN_CASH, "displayName");
	private static final ConfigurationDialogSubmission CONFIGURATION_DIALOG_SUBMISSION = new ConfigurationDialogSubmission(
			"email@test.com");
	private static String IDENTITY_PAYLOAD;
	private static String TRANSACTION_DIALOG_PAYLOAD;
	private static String CONFIGURATION_DIALOG_PAYLOAD;

	@Autowired
	private DialogPayloadParser dialogPayloadParser;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		IDENTITY_PAYLOAD = readFileToString(getFile(DIALOGS_RESOURCE_FOLDER_CLASSPATH + "identityPayload.json"));

		TRANSACTION_DIALOG_PAYLOAD = readFileToString(
				getFile(DIALOGS_RESOURCE_FOLDER_CLASSPATH + "transactionDialogPayload.json"));

		CONFIGURATION_DIALOG_PAYLOAD = readFileToString(
				getFile(DIALOGS_RESOURCE_FOLDER_CLASSPATH + "configurationDialogPayload.json"));
	}

	@Test
	public void testParseIdentity() {
		assertThat(dialogPayloadParser.parseIdentity(IDENTITY_PAYLOAD), equalTo(DIALOG_IDENTITY_PAYLOAD));
	}

	@Test
	public void testParseTransactionDialogSubmission() {
		assertThat(dialogPayloadParser.parseSubmission(TRANSACTION_DIALOG_PAYLOAD, TransactionDialogSubmission.class),
				equalTo(TRANSACTION_DIALOG_SUBMISSION));
	}

	@Test
	public void testParseConfigurationDialogSubmission() {
		assertThat(
				dialogPayloadParser.parseSubmission(CONFIGURATION_DIALOG_PAYLOAD, ConfigurationDialogSubmission.class),
				equalTo(CONFIGURATION_DIALOG_SUBMISSION));
	}
}
