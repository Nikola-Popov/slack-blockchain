package io.slack.blockchain.domain.persitence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "USERS")
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String slackUserId;

	@NotNull
	private String slackTeamId;

	@NotNull
	private String email;

	public User(String name, String slackUserId, String slackTeamId, String email) {
		this.name = name;
		this.slackUserId = slackUserId;
		this.slackTeamId = slackTeamId;
		this.email = email;
	}
}
