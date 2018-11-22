package io.slack.blockchain.coinbase.broker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Balance {
	@JsonProperty("amount")
	private Double amount;

	@JsonProperty("currency")
	private String currency;
}
