package io.slack.blockchain.coinbase.broker.domain;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoinbaseOAuthResponse {
	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private Integer expiresIn;

	@SerializedName("refresh_token")
	private String refreshToken;

	@SerializedName("scope")
	private String scope;

	@SerializedName("created_at")
	private Integer createdAt;
}
