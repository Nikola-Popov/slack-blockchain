package io.slack.blockchain.processing.payload;

import com.github.seratch.jslack.api.webhook.Payload;

public interface ResponsePayloadBuilder {
	Payload buildResponsePayload();
}
