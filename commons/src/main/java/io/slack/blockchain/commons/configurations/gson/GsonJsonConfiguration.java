package io.slack.blockchain.commons.configurations.gson;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.seratch.jslack.api.model.Attachment;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

@Configuration
public class GsonJsonConfiguration {
	@Bean
	public Gson createConfiguredAttachmentUnfurlsIgnoringGson() {
		return new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
				.setExclusionStrategies(new ExclusionStrategy() {

					@Override
					public boolean shouldSkipField(FieldAttributes fieldAttributes) {
						if (fieldAttributes.getDeclaringClass() != Attachment.class) {
							return false;
						}

						return fieldAttributes.getName().equals("msgUnfurl")
								|| fieldAttributes.getName().equals("replyUnfurl");
					}

					@Override
					public boolean shouldSkipClass(Class<?> clazz) {
						return false;
					}
				}).create();
	}

	@Bean
	public JsonParser createJsonParser() {
		return new JsonParser();
	}
}
