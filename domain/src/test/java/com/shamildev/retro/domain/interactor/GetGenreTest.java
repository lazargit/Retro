package com.shamildev.retro.domain.interactor;




import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.testdata.TestData;
import com.shamildev.retro.domain.util.Constants;

import org.junit.Before;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Flowable;



/**
 * Created by Schamil Mischijew on 21.11.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetGenreTest {



    private static final String language = "de-DE";

   @InjectMocks GetGenre getGenre;

   @Mock private RemoteRepository repository;
   @Mock
   private CacheRepository cache;


    @Before
    public void setUp() throws Exception {


      //  GetGenre getGenre = new GetGenre(repository, cache);


    }

    @Test
    public void testGetGenreHappyCase() {


        Mockito.when(repository.fetchGenre(Constants.MEDIA_TYPE.MOVIE))
                .thenReturn(Flowable.empty());
        Mockito.when(repository.fetchGenre(Constants.MEDIA_TYPE.TV))
                .thenReturn(Flowable.empty());


        Mockito.when(cache.fetchGenre(Constants.MEDIA_TYPE.MOVIE,language))
                .thenReturn(Flowable.empty());
        Mockito.when(cache.fetchGenre(Constants.MEDIA_TYPE.TV,language))
                .thenReturn(Flowable.empty());

        Mockito.when(getGenre.fetchAllGenreFromCache(language))
                .thenReturn(Flowable.empty());

        GetGenre.Params with = GetGenre.Params.with(language, 1);
        getGenre.execute(with);

        Mockito.verify(repository).fetchGenre(Constants.MEDIA_TYPE.MOVIE);
        Mockito.verify(repository).fetchGenre(Constants.MEDIA_TYPE.TV);
       Mockito.verify(getGenre.fetchAllGenreFromCache(language)).subscribe();
       // verify(repository).fetchGenre(Constants.MEDIA_TYPE.MOVIE);


    }

    @Test
    public void testGetGenreUseCase2() throws Exception {

        final int isCacheTimeExpired = 1;
        // get all data from cache






    }


}
