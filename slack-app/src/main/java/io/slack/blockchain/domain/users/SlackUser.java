package io.slack.blockchain.domain.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SlackUser {
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class User {
		private String name;
		private String id;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Team {
		private String id;
	}
}
