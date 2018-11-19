package io.slack.blockchain.coinbase.security.oauth.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountsResponse {
	private List<Account> accounts;
}
