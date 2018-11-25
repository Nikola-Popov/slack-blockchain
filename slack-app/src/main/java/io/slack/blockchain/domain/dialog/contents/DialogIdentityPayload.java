package io.slack.blockchain.domain.dialog.contents;

import static io.slack.blockchain.interactive.components.dialogs.constants.DialogPayloadConstants.CALLBACK_ID;
import static io.slack.blockchain.interactive.components.dialogs.constants.DialogPayloadConstants.RESPONSE_URL;

import com.google.gson.annotations.SerializedName;

import io.slack.blockchain.domain.users.SlackUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DialogIdentityPayload {
	private SlackUser.User user;

	private SlackUser.Team team;

	@SerializedName(CALLBACK_ID)
	private String callbackId;

	@SerializedName(RESPONSE_URL)
	private String responseUrl;
}
