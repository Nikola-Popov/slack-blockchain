package io.slack.blockchain.commons.factories.json;

import org.springframework.stereotype.Component;

import com.google.gson.JsonParser;

@Component
public class JsonParserFactory {
	public JsonParser createJsonParser() {
		return new JsonParser();
	}
}
