package io.transaction.broker.coinbase.security.oauth.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OAuthResponse {
	@SerializedName("access_token")
	@Expose
	public String accessToken;

	@SerializedName("token_type")
	@Expose
	public String tokenType;

	@SerializedName("expires_in")
	@Expose
	public Integer expiresIn;

	@SerializedName("refresh_token")
	@Expose
	public String refreshToken;

	@SerializedName("scope")
	@Expose
	public String scope;
}
