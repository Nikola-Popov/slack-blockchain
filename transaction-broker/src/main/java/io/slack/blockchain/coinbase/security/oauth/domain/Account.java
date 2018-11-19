package io.slack.blockchain.coinbase.security.oauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("primary")
	private Boolean primary;

	@JsonProperty("type")
	private String type;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("balance")
	private Balance balance;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	@JsonProperty("resource")
	private String resource;

	@JsonProperty("resource_path")
	private String resourcePath;
}