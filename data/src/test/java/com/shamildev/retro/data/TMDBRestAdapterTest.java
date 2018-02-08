package com.shamildev.retro.data;

import android.media.Image;

import com.shamildev.retro.data.config.DataConfig;
import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.mapper.EntityListMapper;
import com.shamildev.retro.data.entity.mapper.EntityMapper;
import com.shamildev.retro.data.entity.mapper.EntityMapperHolder;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.response.CreditsResponse;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.data.entity.tmdb.response.MovieResponse;
import com.shamildev.retro.data.net.DataServiceFactory;
import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.data.net.error.ErrorInterceptor;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.data.repository.TMDBRepository;
import com.shamildev.retro.data.repository.TMDBRepository_Factory;
import com.shamildev.retro.data.utils.JsonFileResource;
import com.shamildev.retro.data.utils.JsonParsingRule;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;


import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * Created by R_KAY on 11/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TMDBRestAdapterTest {



    private static final String UTF_8_CODING = "UTF-8";
    private static final String API_KEY = "1234567890";
    private static final String LANGUAGE_US = "en-US";
    private static final String LANGUAGE_DE = "de-DE";
    private static final String COUNTRY_DE = "DE";
    private static final String COUNTRY_US= "US";

    private static final String TESTMOVIE_ID= "155";
    private static final String TESTMOVIE_TITLE= "The Dark Knight";
    private static final String TESTMOVIE_TAGLINE= "Why So Serious?";

    //MWS is what we'll use to test the REST Adapter
    private MockWebServer server;

    @Mock
    private EntityMapper<ResponseEntity, MovieWrapper> movieWrapperEntityMapper;

    @Mock
    private DataConfig dataConfig;

    private TMDBServices adapter;


    @InjectMocks
    private TMDBRepository repository;

    @InjectMocks
    private DataServiceFactory dataServiceFactory;

    @InjectMocks
    private  EntityMapperHolder entityMapperHolder;





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

        TestObserver<MovieResponse> test = adapter.fetchMovie(TESTMOVIE_ID, API_KEY, LANGUAGE_DE, "")
                .test()
                .assertSubscribed()
                .assertComplete()
                .assertNoErrors();





        test.assertValue(movieResponse -> (movieResponse != null));
        test.assertValue(movieResponse -> Objects.equals(movieResponse.getId(), Long.parseLong(TESTMOVIE_ID)));
        test.assertValue(movieResponse -> Objects.equals(movieResponse.getTitle(), TESTMOVIE_TITLE));
        test.assertValue(movieResponse -> movieResponse.getGenres().size()>0);
        test.assertValue(movieResponse -> (Objects.equals(movieResponse.getGenres().get(0).getId(),18)
                                           &&  Objects.equals(movieResponse.getGenres().get(0).getName(),"Drama")));
        test.assertValue(movieResponse -> Objects.equals(movieResponse.getTagline(), TESTMOVIE_TAGLINE));



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





    @Test
    @JsonFileResource(fileName = "UpcomingMoviesResponse.json", clazz = String.class)
    public void on_FetchUpcomingMovies_Successful() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonParsingRule.getValue().toString()));

//       adapter.fetchUpcomingMovies(API_KEY, "1", LANGUAGE_US, COUNTRY_US)
//
//                .test()
//                .assertSubscribed()
//                .assertComplete()
//                .assertNoErrors()
//               ;

        ResponseEntity responseEntity = adapter.fetchUpcomingMovies(API_KEY, "1", LANGUAGE_US, COUNTRY_US).toFlowable()

                .blockingSingle();
        List<Result> results = responseEntity.getResults();


        Result result = results.get(0);

        System.out.println("result" + result.getTitle());


        Movie build = Movie.builder()
                .id(result.getId())
                .title(result.getTitle())
                .overview(result.getOverview())

                .originalTitle(result.getTitle())
                .originalLanguage(result.getOriginalLanguage())

                .posterPath(result.getPosterPath())
                .backdropPath(result.getBackdropPath())

                .genreIds(result.getGenreIds())
                .adult(result.getAdult())
                .video(result.getVideo())


                .releaseDate(result.getReleaseDate())


                .popularity(result.getPopularity())
                .voteAverage(result.getVoteAverage())
                .voteCount(result.getVoteCount())

                .build();

        List<Movie> movieList = new ArrayList<>();
                    movieList.add(build);




        System.out.println("build"+build.originalTitle());
        System.out.println("result"+result.getOriginalTitle());

      //  map(result);

        Mockito.when(entityMapperHolder.movieEntityMapper().map(result))
                .thenReturn(build);

        Movie map = entityMapperHolder.movieEntityMapper().map(result);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+map.originalTitle());



        System.out.println("responseEntity" + responseEntity.getPage());

        MovieWrapper build1 = MovieWrapper.builder().totalPages(1).totalResults(2).page(1).results(movieList).build();

        Mockito.when(entityMapperHolder.movieWrapperEntityMapper().map(responseEntity))
                .thenReturn(build1);

        MovieWrapper map1 = entityMapperHolder.movieWrapperEntityMapper().map(responseEntity);

        System.out.println("<>>>>>>>>>>>>>>>>>>>>>>>"+map1.results().size());



        //List<Movie> movies = mapToV(entityMapperHolder.movieEntityMapper(), results);



      //  ;

    }







    @Test
    @JsonFileResource(fileName = "ImagesResponse.json", clazz = String.class)
    public void on_FetchImages_Successful() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonParsingRule.getValue().toString()));

        ImagesResponse imagesResponseTestData;
        TestObserver<ImagesResponse> test = adapter.fetchImages(API_KEY)

                .test()
                .assertComplete()
                .assertNoErrors();




        ImagesResponse imagesResponse1 = test.values().get(0);

        for (PosterEntity  posterEntity:imagesResponse1.getPosters()) {
            ImageModel imageModel = ImageModel.builder()
                    .aspectRatio(posterEntity.getAspectRatio())
                    .filePath(posterEntity.getFilePath())
                    .voteAverage(posterEntity.getVoteAverage())
                    .voteCount(posterEntity.getVoteCount())
                    .iso6391(posterEntity.getIso6391())
                    .height(posterEntity.getHeight())
                    .width(posterEntity.getWidth())
                    .build();
            Mockito.when(entityMapperHolder.posterEntityMapper().map(posterEntity))
                    .thenReturn(imageModel);


        }



        test.assertValue(imagesResponse -> (imagesResponse != null));

        test.assertValue(imagesResponse -> Objects.equals(imagesResponse.getId(), Integer.parseInt(TESTMOVIE_ID)));
        test.assertValue(imagesResponse -> (imagesResponse.getBackdrops().size() > 0));
        test.assertValue(imagesResponse -> (imagesResponse.getPosters().size() > 0));
        test.assertValue(imagesResponse -> (imagesResponse.getBackdrops().get(0).getFilePath().equals("/hqkIcbrOHL86UncnHIsHVcVmzue.jpg")));
        test.assertValue(imagesResponse -> (imagesResponse.getPosters().get(0).getFilePath().equals("/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg")));
        test.assertValue(imagesResponse -> (imagesResponse.getPosters().get(0).getIso6391().equals("en")));




        List<ImageModel> imageModels = Observable.just(imagesResponse1.getPosters())
                .map(posterEntities -> Flowable.fromIterable(posterEntities)
                        .map(posterEntities1 -> entityMapperHolder.posterEntityMapper().map(posterEntities1))
                        .toList()
                        .blockingGet())
                .blockingSingle();
        System.out.println(imagesResponse1.getPosters().size()+"<>>>>>>>>>>>>>>>>>>>>>>>" + imageModels.size());

        Images build = Images.builder()
                .id(155)
                .posters(imageModels)
                .backdrops(imageModels)
                .build();

        DataConfig build1 = DataConfig.builder()
                .language(LANGUAGE_DE)
                .authClientId(API_KEY)
                .baseUrl("XXX")
                .authGrantType("")
                .authClientSecret("")
                .cacheRootDir("")
                .cacheMaxSizeMb(1)
                .cacheDir("")
                .offlineCacheTimeDays(1)
                .networkCacheTimeSeconds(10)
                .debug(true)
                .country("de")
                .build();


        System.out.println("qqqqq< " + build1.baseUrl());
        build1 = build1.setBaseUrl("test");
        System.out.println("qqqqq> " + build1.baseUrl());
        build1 = build1.setLanguage(LANGUAGE_US);
        System.out.println("qqqqq> " + build1.language());

//        Mockito.when( dataConfig.authClientSecret())
//                .thenReturn(API_KEY);



//        repository.fetchImages(Integer.valueOf(TESTMOVIE_ID))
//                .test();






    }

    public void onSuccess(ImagesResponse imagesResponse) {

    }



    @Test
    @JsonFileResource(fileName = "CreditsResponse.json", clazz = String.class)
    public void on_FetchCredits_Successful() throws Exception {

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonParsingRule.getValue().toString()));

        TestObserver<CreditsResponse> test = adapter.fetchCredits(API_KEY)
                .test()
                .assertComplete()
                .assertNoErrors();

        test.assertValue(creditsResponse -> (creditsResponse != null));
        test.assertValue(creditsResponse -> Objects.equals(creditsResponse.getId(), Integer.parseInt(TESTMOVIE_ID)));
        test.assertValue(creditsResponse -> (creditsResponse.getCast().size() > 0));
        test.assertValue(creditsResponse -> (creditsResponse.getCrew().size() > 0));
        test.assertValue(creditsResponse -> Objects.equals( creditsResponse.getCast().get(0).getName(),"Christian Bale")) ;
        test.assertValue(creditsResponse -> Objects.equals(creditsResponse.getCast().get(0).getCastId(),35));

        test.assertValue(creditsResponse -> Objects.equals( creditsResponse.getCrew().get(0).getName(),"Christopher Nolan")) ;
        test.assertValue(creditsResponse -> Objects.equals(creditsResponse.getCrew().get(0).getJob(),"Director"));

    }





    @Test
    public void on_mapping_test() throws Exception {
        ResponseEntity responseEntity = new ResponseEntity();
                       responseEntity.setPage(1);
                       responseEntity.setTotalPages(2);
                       responseEntity.setTotalResults(10);
        System.out.println("responseEntity" + responseEntity.getPage());

        MovieWrapper build1 = MovieWrapper.builder().totalPages(1).totalResults(2).page(1).build();

        Mockito.when(entityMapperHolder.movieWrapperEntityMapper().map(responseEntity))
                .thenReturn(build1);

        MovieWrapper map1 = entityMapperHolder.movieWrapperEntityMapper().map(responseEntity);

        System.out.println("<>>>>>>>>>>>>>>>>>>>>>>>"+map1.page());

    }



    public Movie map(Result entity) {


        System.out.println("Mapper>>>>>"+entity.getTitle());
        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .overview(entity.getOverview())

                .originalTitle(entity.getTitle())
                .originalLanguage(entity.getOriginalLanguage())

                .posterPath(entity.getPosterPath())
                .backdropPath(entity.getBackdropPath())

                .genreIds(entity.getGenreIds())
                .adult(entity.getAdult())
                .video(entity.getVideo())





                .releaseDate(entity.getReleaseDate())


                .popularity(entity.getPopularity())
                .voteAverage(entity.getVoteAverage())
                .voteCount(entity.getVoteCount())

                .build();
    }


    <K extends Entity, V extends DomainObject> List<V> mapToV(EntityMapper<K, V> entityMapper,
                                                              List<K> kList) {
        List<V> vList = new ArrayList<>(kList.size());
        for (K k : kList) {
            vList.add(entityMapper.map(k));
        }
        return vList;
    }


    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }
}
