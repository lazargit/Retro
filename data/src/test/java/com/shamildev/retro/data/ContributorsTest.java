package com.shamildev.retro.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shamildev.retro.data.entity.Contributor;
import com.shamildev.retro.data.utils.JsonFileResource;
import com.shamildev.retro.data.utils.JsonParsingRule;
import com.shamildev.retro.domain.util.Constants;

import org.junit.Rule;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContributorsTest {
	private static final String UTF_8_CODING = "UTF-8";
	private  Gson mGson;
	private Object mValue;

@Rule public JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);
	private String outputJson;


	@Test
@JsonFileResource(fileName = "contributors.json", clazz = Contributor[].class)
public void testGetContributors() throws Exception {

		jsonParsingRule.getValue();
	//assertThat(contributors).hasSize(2);
	//assertThat(contributors[0].login).isEqualTo("koral--");
}

	@Test
	public void testSomethingElse() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("contributors.json");
		//Context context = mock(Context.class);
		//Resources resources = mock(Resources.class);
		//when(resources.openRawResource(anyInt())).thenReturn(in);
		//when(context.getResources()).thenReturn(resources);

		//mGson = new Gson();

		InputStreamReader inputData = new InputStreamReader(in);

		try {
			InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream("contributors.json");
			int size = inputStream.available();
			byte[] buffer = new byte[size];
			inputStream.read(buffer);
			inputStream.close();
			outputJson = new String(buffer, UTF_8_CODING);

		} catch (IOException ignore) {
			ignore.printStackTrace();
		}
		System.out.println(">>>>>Received: " +outputJson);
//		try(Reader bufferedReader = new BufferedReader(inputData)) {
//			Contributor contributor = mGson.fromJson(bufferedReader, Contributor.class);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}



		//assertThat(jsonParsingRule.getValue()).isNull();
	}


	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Test
	public void testParseHardcodedContributors() throws Exception {
		//given
		String json = "[\n" +
				"  {\n" +
				"    \"login\": \"koral--\",\n" +
				"    \"id\": 3340954,\n" +
				"    \"site_admin\": true\n" +
				"  },\n" +
				"  {\n" +
				"    \"login\": \"Wavesonics\",\n" +
				"    \"id\": 406473,\n" +
				"    \"site_admin\": false\n" +
				"  }\n" +
				"]\n";

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gsonBuilder.create();

		//when
		Contributor[] contributors;
		try (Reader reader = new BufferedReader(new StringReader(json))) {
			contributors = gson.fromJson(reader, Contributor[].class);
		}

		//then
		assertThat(contributors).hasSize(2);
		Contributor contributor = contributors[0];
		assertThat(contributor.login).isEqualTo("koral--");
		assertThat(contributor.siteAdmin).isTrue();
	}
}