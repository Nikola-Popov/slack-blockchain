package io.slack.blockchain.domain.users;

import lombok.Data;

public class SlackUser {
	@Data
	public static class User {
		private String name;
		private String id;
	}

	@Data
	public static class Team {
		private String id;
	}
}
