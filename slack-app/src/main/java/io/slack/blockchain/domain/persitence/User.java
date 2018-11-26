package io.slack.blockchain.domain.persitence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "USERS")
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;
	private String slackUserId;
	private String slackTeamId;
	private String email;

	public User(String name, String slackUserId, String slackTeamId, String email) {
		this.name = name;
		this.slackUserId = slackUserId;
		this.slackTeamId = slackTeamId;
		this.email = email;
	}
}
