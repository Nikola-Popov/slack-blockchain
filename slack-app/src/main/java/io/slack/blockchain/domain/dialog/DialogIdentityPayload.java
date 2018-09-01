package io.slack.blockchain.domain.dialog;

import static io.slack.blockchain.interactive.components.dialogs.constants.DialogPayloadConstants.CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.constants.DialogPayloadConstants.RESPONSE_URL;

import com.google.gson.annotations.SerializedName;

import io.slack.blockchain.domain.users.SlackUser;
import lombok.Data;

@Data
public class DialogIdentityPayload {
	private SlackUser.User user;

	private SlackUser.Team team;

	@SerializedName(CALLBACK_ID)
	private String callbackId;

	@SerializedName(RESPONSE_URL)
	private String responseUrl;
}
