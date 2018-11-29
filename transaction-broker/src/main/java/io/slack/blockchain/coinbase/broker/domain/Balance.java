package io.slack.blockchain.coinbase.broker.domain;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Balance {
	@SerializedName("amount")
	private Double amount;

	@SerializedName("currency")
	private String currency;
}
