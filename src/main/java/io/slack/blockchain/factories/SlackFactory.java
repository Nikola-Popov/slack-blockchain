package io.slack.blockchain.factories;

import org.springframework.stereotype.Component;

import com.github.seratch.jslack.Slack;

@Component
public class SlackFactory {
	public Slack createSlack() {
		return Slack.getInstance();
	}
}
