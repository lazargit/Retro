package com.shamildev.retro.data;

import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.net.DataServiceFactory;
import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.data.net.error.ErrorInterceptor;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.data.utils.JsonFileResource;
import com.shamildev.retro.data.utils.JsonParsingRule;
import com.shamildev.retro.domain.util.Constants;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by R_KAY on 11/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TMDBRestAdapterTest {

    //MWS is what we'll use to test the REST Adapter
    private MockWebServer server;


    private TMDBServices adapter;

    @InjectMocks
    private DataServiceFactory dataServiceFactory;

    @Rule
    public JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);

    /**
     * Using MockWebServer
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        OkHttpClient client =
                new OkHttpClient.Builder()
                        .addInterceptor(
                                new ErrorInterceptor()
                        ).build();

        Retrofit build = new Retrofit.Builder()
                .client(client)
                .baseUrl(
                        server.url("/").toString()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        adapter = build.create(TMDBServices.class);


    }

    /**
     * Server responds with:
     * Status: 200 OK (200 status code indicates successful request/response from server)
     *
     */
    @Test
    @JsonFileResource(fileName = "ConfigurationResponse.json", clazz = String.class)
    public void on_FetchConfiguration_Successful() throws Exception {

         server.enqueue(new MockResponse()
        .setResponseCode(200)
        .setBody(jsonParsingRule.getValue().toString()));

        TestObserver<ConfigurationResponseEntity> test = adapter.fetchConfiguration("1232456")
                .test()
                .assertComplete()
                .assertNoErrors();

    }
    @Test
    @JsonFileResource(fileName = "ErrorEntity.json", clazz = String.class)
    public void on_FetchConfiguration_Failed() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody(jsonParsingRule.getValue().toString()));


        adapter.fetchConfiguration("XXX")
                .test()
                .assertError(TMDBError.class);



    }




    @Test
    @JsonFileResource(fileName = "GenresResponse.json", clazz = String.class)
    public void on_FetchGenre_Successful() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonParsingRule.getValue().toString()));

       adapter.fetchGenre("movie","123456","de_DE")
                .test()
                .assertComplete()
                .assertNoErrors();

    }

    @Test
    @JsonFileResource(fileName = "ErrorEntity.json", clazz = String.class)
    public void on_FetchGenre_Failed() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody(jsonParsingRule.getValue().toString()));

        adapter.fetchGenre("movie","XXX","de_DE")
                .test()
                .assertError(TMDBError.class);

    }




    @Test
    @JsonFileResource(fileName = "MovieDetailsResponse.json", clazz = String.class)
    public void on_FetchMovie_Successful() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonParsingRule.getValue().toString()));

        adapter.fetchMovie("155","1234","de_DE","")
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors();

    }

    @Test
    @JsonFileResource(fileName = "ErrorEntity.json", clazz = String.class)
    public void on_FetchMovie_Failed_ApiKey() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody(jsonParsingRule.getValue().toString()));

        adapter.fetchMovie("155","XXX","de_DE","")
                .test()
                .assertError(TMDBError.class);

    }




    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }
}
