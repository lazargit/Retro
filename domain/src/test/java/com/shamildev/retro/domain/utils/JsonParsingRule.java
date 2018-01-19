package com.shamildev.retro.domain.utils;



import com.google.gson.Gson;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.InputStream;

public class JsonParsingRule implements TestRule {
	private final Gson mGson;
	private Object mValue;
	private static final String UTF_8_CODING = "UTF-8";

	public JsonParsingRule(Gson gson) {
		mGson = gson;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) mValue;
	}

	@Override
	public Statement apply(final Statement base, final Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				JsonFileResource jsonFileResource = description.getAnnotation(JsonFileResource.class);

				if (jsonFileResource != null) {
					Class<?> clazz = jsonFileResource.clazz();
					String resourceName = jsonFileResource.fileName();
					Class<?> testClass = description.getTestClass();

					try {
						InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(resourceName);
						int size = inputStream.available();
						byte[] buffer = new byte[size];
						inputStream.read(buffer);
						inputStream.close();
						mValue = new String(buffer, UTF_8_CODING);

					} catch (IOException ignore) {
						ignore.printStackTrace();
					}

				}
				base.evaluate();
			}
		};
	}
}
