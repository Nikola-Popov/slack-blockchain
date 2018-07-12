package io.slack.blockchain.domain;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlackUser {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;

	private String userId;

	private String teamId;

	private String cryptoCurrencyAddress;
}
