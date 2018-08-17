package com.shamildev.retro.domain.interactor;


import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.params.Product;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.testdata.TestData;
import com.shamildev.retro.domain.util.DateUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;


/**
 * Created by Schamil Mischijew on 21.11.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetTMDBConfigurationTest {





    private static final int CACHE_TIME = 5;

   @InjectMocks GetTMDBConfiguration getTMDBConfiguration;



   @Mock
   private RemoteRepository repository;
   @Mock
   private CacheRepository cache;




    private ClassLoader classLoader;


    @Before
    public void setUp() throws Exception {

         classLoader = this.getClass().getClassLoader();




    }

    public Flowable<Configuration> saveToCache(Configuration model ) {

        System.out.println("saveToCache>");
        return   Flowable.defer(() -> {

            try {

                return Flowable.just(model)
                        .flatMap(configModel -> Flowable.just(configModel)
                                .flatMapCompletable(cache::saveTMDbConfiguration)
                                .toFlowable()
                                .startWith(configModel)

                        ).cast(Configuration.class);

            } catch (Exception e) {

                return Flowable.error(e);

            }
        });


    }
    public Flowable<Configuration> fetchConfigurationFromNet() {
        System.out.println("fetchConfigurationFromNet>");

        return   repository.fetchConfiguration();


    }

    public Flowable<Configuration> fetchConfigurationFromCache() {
        System.out.println("fetchConfigurationFromCache>");

        return  cache.fetchConfiguration();
    }

    public Flowable<Configuration> executeTest() {
        System.out.println("executeTest");
        int cacheTime = 1;






     return Flowable.just(fetchConfigurationFromCache())

                .map(genreFlowable -> {

                    System.out.println(">>>>>>>"+genreFlowable.isEmpty().blockingGet());
                     if(genreFlowable.isEmpty().blockingGet()){
                         return fetchConfigurationFromNet();
                     }


                    return genreFlowable;
                })
         //    .map(model -> DateUtil.isCacheTimeExpired(model,cacheTime)
           //             .flatMap(aBoolean ->  fetchConfigurationFromNet()).blockingLast());
                .map(new Function<Flowable<Configuration>, Flowable<Configuration>>() {
                    @Override
                    public Flowable<Configuration> apply(Flowable<Configuration> configurationFlowable) throws Exception {
                      return   configurationFlowable
                                .map(new Function<Configuration, Boolean>() {
                            @Override
                            public Boolean apply(Configuration configuration) throws Exception {

                                return DateUtil.isCacheTimeExpired(configuration, cacheTime).blockingSingle();
                            }
                        }).flatMap(aBoolean -> (aBoolean) ? fetchConfigurationFromNet() : configurationFlowable);




                    }
                })
             .blockingSingle()




                ;



              //  .switchIfEmpty(fetchConfigurationFromNet());






                      //  .flatMap(aBoolean ->  fetchConfigurationFromNet()).blockingLast());



    }


    @Test
    public void test_rx() throws Exception {
        Flowable<Configuration> configurationFlowableRepoData = TestData.REPO_TMDB_CONFIGURATION_DATA(classLoader);





        Mockito.when(repository.fetchConfiguration())
                .thenReturn(configurationFlowableRepoData);

        Mockito.when(cache.fetchConfiguration())
         .thenReturn(Flowable.empty());
        // .thenReturn(TestData.CACHE_TMDB_CONFIGURATION_DATA(classLoader,2));



        Mockito.when(cache.saveTMDbConfiguration(Mockito.any(Configuration.class)))
                .thenReturn(Completable.complete());


        executeTest()





                .subscribe(new DisposableSubscriber<Configuration>() {
                    @Override
                    public void onNext(Configuration configuration) {
                        System.out.println("onNext>>>"+configuration);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError"+t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete>>>");
                    }
                })
               ;

    }



    @Test
    public void test_GetTMDBConfiguration_HappyCase_with_validCacheData() throws Exception {

//        Mockito.when(repository.fetchConfiguration())
//                .thenReturn(TestData.REPO_TMDB_CONFIGURATION_DATA(classLoader));

//        Mockito.when(cache.fetchConfiguration())
//                .thenReturn(TestData.CACHE_TMDB_CONFIGURATION_DATA(classLoader,2));

        Configuration saveConfiguration = TestData.REPO_TMDB_CONFIGURATION_DATA(classLoader).blockingSingle();

        System.out.println(">>>"+saveConfiguration.lastUpdate());

        Mockito.when(cache.fetchConfiguration())
                .thenReturn(TestData.CACHE_TMDB_CONFIGURATION_DATA(classLoader,2));

        Mockito.when(cache.saveTMDbConfiguration(saveConfiguration))
                .thenReturn(Completable.complete());

        GetTMDBConfiguration.Params with = GetTMDBConfiguration.Params.withCacheTime(CACHE_TIME);
        TestSubscriber<Configuration > subscriber = new TestSubscriber<>();
        getTMDBConfiguration.execute(with).subscribe(subscriber);

        Mockito.verify(repository, times(1)).fetchConfiguration();
        Mockito.verify(cache, times(1)).fetchConfiguration();
        Mockito.verify(cache, never()).saveTMDbConfiguration(saveConfiguration);

        subscriber.assertNoErrors();
        subscriber.assertComplete();
        subscriber.dispose();
        subscriber.assertValueCount(1);
      //  subscriber.assertValue(TestData.CACHE_TMDB_CONFIGURATION_DATA(classLoader,0).blockingSingle());

    }


    @Test
    public void test_GetTMDBConfiguration_HappyCase_with_emptyCacheData() throws Exception {



        Flowable<Configuration> configurationFlowableRepoData = TestData.REPO_TMDB_CONFIGURATION_DATA(classLoader);






        Mockito.when(repository.fetchConfiguration())
                .thenReturn(configurationFlowableRepoData);

        Mockito.when(cache.fetchConfiguration())
                .thenReturn(TestData.CACHE_TMDB_CONFIGURATION_DATA(classLoader,2));



        Mockito.when(cache.saveTMDbConfiguration(Mockito.any(Configuration.class)))
                .thenReturn(Completable.complete());



        GetTMDBConfiguration.Params with = GetTMDBConfiguration.Params.withCacheTime(CACHE_TIME);
        TestSubscriber<Configuration > subscriber = new TestSubscriber<>();
        getTMDBConfiguration.execute(with).subscribe(subscriber);



        subscriber.assertNoErrors();
        subscriber.assertComplete();
        subscriber.dispose();
        subscriber.assertValueCount(1);




    }


}
