package io.slack.blockchain.domain.users;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "USERS")
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

	@Email
	private String email;

	private boolean isAuthorized;
}
