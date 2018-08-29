package io.slack.blockchain.domain.messages;

import static java.util.Arrays.asList;

import java.util.List;

import com.github.seratch.jslack.api.model.Action;

import lombok.Data;

@Data
public class AttachmentAction {
	private final List<Action> actions;
	private String fallback;

	public AttachmentAction(final Action action, final String fallback) {
		actions = asList(action);
		this.fallback = fallback;
	}
}
