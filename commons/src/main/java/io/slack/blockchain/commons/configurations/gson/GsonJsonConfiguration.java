package io.slack.blockchain.commons.configurations.gson;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

@Configuration
public class GsonJsonConfiguration {
	@Bean
	public Gson createGson() {
		return new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
	}

	@Bean
	public JsonParser createJsonParser() {
		return new JsonParser();
	}
}
