package io.slack.blockchain.coinbase.broker.domain;

import com.google.gson.annotations.SerializedName;

public class Account {
	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("primary")
	private Boolean primary;

	@SerializedName("type")
	private String type;

	@SerializedName("currency")
	private String currency;

	@SerializedName("balance")
	private Balance balance;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("resource")
	private String resource;

	@SerializedName("resource_path")
	private String resourcePath;
}