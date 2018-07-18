package io.slack.blockchain.commons;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

@Component
public class GsonJsonService {
	@Autowired
	private Gson gson;

	@Autowired
	private JsonParser jsonParser;

	public JsonElement parse(String json) throws JsonSyntaxException {
		return jsonParser.parse(json);
	}

	public JsonElement parse(Reader json) throws JsonIOException, JsonSyntaxException {
		return jsonParser.parse(json);
	}

	public JsonElement parse(JsonReader json) throws JsonIOException, JsonSyntaxException {
		return jsonParser.parse(json);
	}

	public GsonBuilder newBuilder() {
		return gson.newBuilder();
	}

	public boolean serializeNulls() {
		return gson.serializeNulls();
	}

	public JsonElement toJsonTree(Object src) {
		return gson.toJsonTree(src);
	}

	public JsonElement toJsonTree(Object src, Type typeOfSrc) {
		return gson.toJsonTree(src, typeOfSrc);
	}

	public String toJson(Object src) {
		return gson.toJson(src);
	}

	public String toJson(Object src, Type typeOfSrc) {
		return gson.toJson(src, typeOfSrc);
	}

	public void toJson(Object src, Appendable writer) throws JsonIOException {
		gson.toJson(src, writer);
	}

	public void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
		gson.toJson(src, typeOfSrc, writer);
	}

	public void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
		gson.toJson(src, typeOfSrc, writer);
	}

	public String toJson(JsonElement jsonElement) {
		return gson.toJson(jsonElement);
	}

	public void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
		gson.toJson(jsonElement, writer);
	}

	public JsonWriter newJsonWriter(Writer writer) throws IOException {
		return gson.newJsonWriter(writer);
	}

	public JsonReader newJsonReader(Reader reader) {
		return gson.newJsonReader(reader);
	}

	public void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
		gson.toJson(jsonElement, writer);
	}

	public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
		return gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
		return gson.fromJson(json, typeOfT);
	}

	public <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
		return gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
		return gson.fromJson(json, typeOfT);
	}

	public <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
		return gson.fromJson(reader, typeOfT);
	}

	public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
		return gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
		return gson.fromJson(json, typeOfT);
	}
}
