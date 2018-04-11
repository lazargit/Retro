package com.shamildev.retro.domain.interactor;




import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shamildev.retro.domain.executor.ExecutionThread;
import com.shamildev.retro.domain.executor.PostExecutionThread;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.testdata.TestData;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.utils.JsonParsingRule;

import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;


/**
 * Created by Schamil Mischijew on 21.11.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetGenreTest {


    private static final String UTF_8_CODING = "UTF-8";
    private static final String LANGUAGE_DE = "de-DE";

   @InjectMocks GetGenre getGenre;


    UseCaseHandler useCaseHandler;

   @Mock
   private RemoteRepository repository;
   @Mock
   private CacheRepository cache;

    @Rule
    public JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);

    private  ExecutionThread executionThread = new ExecutionThread() {
        @Override
        public Scheduler scheduler() {
            return Schedulers.io();
        }
    };

    private  PostExecutionThread postExecutionThread = new PostExecutionThread() {
        @Override
        public Scheduler scheduler() {
            return Schedulers.computation();
        }
    };


    @Before
    public void setUp() throws Exception {


      //  GetGenre getGenre = new GetGenre(repository, cache);
       useCaseHandler = new UseCaseHandler(executionThread,postExecutionThread,new CompositeDisposable());



    }



    @Test
    public void testGetGenreUseCaseWhenSuccess() throws Exception {

        final int isCacheTimeExpired = 1;

        ClassLoader classLoader = this.getClass().getClassLoader();

        Mockito.when(cache.fetchGenre(Constants.MEDIA_TYPE.MOVIE, LANGUAGE_DE))
                .thenReturn(TestData.CACHE_GENRE_MOVIE_DATA(classLoader,LANGUAGE_DE));
        Mockito.when(cache.fetchGenre(Constants.MEDIA_TYPE.TV, LANGUAGE_DE))
                .thenReturn(TestData.CACHE_GENRE_TV_DATA(classLoader,LANGUAGE_DE));


//        Mockito.when(cache.fetchGenre(Constants.MEDIA_TYPE.MOVIE, LANGUAGE_DE))
//                .thenReturn(Flowable.empty());
//        Mockito.when(cache.fetchGenre(Constants.MEDIA_TYPE.TV, LANGUAGE_DE))
//                .thenReturn(Flowable.empty());



        Mockito.when(repository.fetchGenre(Constants.MEDIA_TYPE.MOVIE))
                .thenReturn(TestData.REPO_GENRE_MOVIE_DATA(classLoader, LANGUAGE_DE));
        Mockito.when(repository.fetchGenre(Constants.MEDIA_TYPE.TV))
                .thenReturn(TestData.REPO_GENRE_TV_DATA(classLoader, LANGUAGE_DE));





        Mockito.when(cache.saveGenre(Mockito.any(Genre.class)))
                .thenReturn(Completable.complete());




        GetGenre.Params with = GetGenre.Params.with(LANGUAGE_DE, 1);
        TestSubscriber<List<Genre>> subscriber = new TestSubscriber<>();
        getGenre.execute(with).subscribe(subscriber);



        subscriber.assertNoErrors();
        subscriber.assertComplete();
        subscriber.dispose();
        subscriber.assertValueCount(1);




    }





    public void on_FetchGenre_Successful() {







    }

//    public <T> T create(Class<T> serviceClass) {
//
//
//        T t = retrofit(okHttpClientBuilder()).create(serviceClass);
//        Log.d("retrofit","retrofit"+t);
//        return t;
//    }

    private  String getGsonData(InputStream resourceAsStream){
        String s = "";

        if (resourceAsStream != null) {
            // Class<?> clazz = jsonFileResource.clazz();
            // String resourceName = jsonFileResource.fileName();
            //Class<?> testClass = description.getTestClass();

            try {
                InputStream inputStream =  resourceAsStream;
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                s = new String(buffer, UTF_8_CODING);









            } catch (IOException ignore) {
                ignore.printStackTrace();
            }

            JsonParser jsonParser = new JsonParser();
            Gson googleJson = new Gson();
            JsonArray arrayFromString = jsonParser.parse(s).getAsJsonArray();
           Observable.fromIterable(arrayFromString)
                    .map(new Function<JsonElement, Genre>() {
                        @Override
                        public Genre apply(JsonElement jsonElement) throws Exception {

                            JsonObject provider = jsonElement.getAsJsonObject();

                            System.out.println(provider.get("name"));
                            return Genre.builder()
                                    .id(provider.get("id").getAsInt())
                                    .name(provider.get("name").toString()).build();

                        }
                    }).toFlowable(BackpressureStrategy.LATEST).subscribe();

            //    String string = jsonElement.toString();
          //  Genre genre = googleJson.fromJson(string, Genre.class);
          //  System.out.println(genre.name());



        }
        return s;
    }


}
