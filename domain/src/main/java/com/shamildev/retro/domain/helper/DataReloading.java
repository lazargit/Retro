package com.shamildev.retro.domain.helper;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetPopularPerson;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 02.04.2018.
 */

public class DataReloading {

    private final GetPopularPerson getPopularPerson;
    private UseCaseHandler useCaseHandler;
    private final GetUpcomingMovies getUpcomingMovies;
    private final GetTopRatedMovies getTopRatedMovies;
    private final GetNowPlayingMovies getNowPlayingMovies;
    private final GetNowPlayingTVShows getNowPlayingTVShows;
    private final GetMyWatchList getMyWatchList;

    List<DomainObject> watchList;

    DataReloadingListener listener;

    public interface DataReloadingListener {


        void onDataLoad(ResultWrapper resultWrapper);
        void onDataError(Throwable t);

    }
    public interface LoadTopicsListener {

        void onDataLoad();
        void onDataError();

    }


    @Inject
    AppConfig appConfig;


    @Inject
    public DataReloading(UseCaseHandler useCaseHandler,
                         GetUpcomingMovies getUpcomingMovies,
                         GetTopRatedMovies getTopRatedMovies,
                         GetNowPlayingMovies getNowPlayingMovies,
                         GetNowPlayingTVShows getNowPlayingTVShows,
                         GetPopularPerson getPopularPerson,
                         GetMyWatchList getMyWatchList) {

        this.useCaseHandler = useCaseHandler;
        this.getUpcomingMovies = getUpcomingMovies;
        this.getTopRatedMovies = getTopRatedMovies;
        this.getNowPlayingMovies = getNowPlayingMovies;
        this.getNowPlayingTVShows = getNowPlayingTVShows;
        this.getPopularPerson = getPopularPerson;
        this.getMyWatchList = getMyWatchList;


    }



    public void loadTopics(ArrayList<String> mListTopic,HashMap<String, ResultWrapper> map, LoadTopicsListener loadTopicsListener) {

            this.listener = new DataReloadingListener() {
                @Override
                public void onDataLoad(ResultWrapper resultWrapper) {
                    map.put(mListTopic.get(0),resultWrapper);
                    mListTopic.remove(0);
                    if(mListTopic.size()>0){
                        fetchTopic(mListTopic);

                    }else{
                        loadTopicsListener.onDataLoad();
                    }
                    System.out.println("TAGONDATALOAD");
                   //  map.put(topic,resultWrapper);
                }

                @Override
                public void onDataError(Throwable t) {
                    System.out.println("TAGonDataError");
                }
            };

             fetchTopic(mListTopic);



    }

    private void fetchTopic(ArrayList<String> mListTopic) {
        String topic = mListTopic.get(0);
        switch (topic){
                case AppConfig.NOWPLAYINGKEY: loadNowPlaying(1);
                    break;
                case AppConfig.NOWPLAYINGTVKEY: loadNowPlayingTV(1);
                    break;
                case AppConfig.UPCOMMINGKEY: loadUpComming(1);
                    break;
                case AppConfig.TOPRATEDKEY: loadTopRated(1);
                    break;
                case AppConfig.POPULARPERSONKEY: loadPopularPerson(1);
                  break;
                case AppConfig.HOMEHEADERKEY: loadPopularPerson(1);
                  break;
            }
    }


    public void loadMore(int page, String tag,DataReloading.DataReloadingListener listener) {

        this.listener = listener;
        if(tag.equals(AppConfig.NOWPLAYINGKEY)){
            loadNowPlaying(page);
        }
        if(tag.equals(AppConfig.NOWPLAYINGTVKEY)){
            loadNowPlayingTV(page);
        }
        if(tag.equals(AppConfig.UPCOMMINGKEY)){
            loadUpComming(page);
        }
        if(tag.equals(AppConfig.TOPRATEDKEY)){
            loadTopRated(page);
        }


    }


    private void loadNowPlaying(int page){

        useCaseHandler.execute(getNowPlayingMovies, GetNowPlayingMovies.Params.withPage(page), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper movieWrapper) {

                //Log.d("onNext", "totalPages "+movieWrapper.totalPages());
                //addMoreDataToList(movieWrapper);
                listener.onDataLoad(movieWrapper);

            }

            @Override
            public void onError(Throwable t) {
                listener.onDataError(t);
//                if (t.getCause() instanceof TMDBError) {
//                    TMDBError error = (TMDBError) t.getCause();
//                    Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                }
               // Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {
               // Log.d("onComplete", ">>");
               // refreshCurrentPage(page);
            }
        });
    }


    private void loadNowPlayingTV(int page){

        useCaseHandler.execute(getNowPlayingTVShows, GetNowPlayingTVShows.Params.withPage(page), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper movieWrapper) {
                listener.onDataLoad(movieWrapper);
               // Log.d("onNext", "totalPages "+movieWrapper.totalPages());
             //   movieList.addAll(prepareData(movieWrapper.results()));
              //  recyclerViewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable t) {
                listener.onDataError(t);
//                if (t.getCause() instanceof TMDBError) {
//                    TMDBError error = (TMDBError) t.getCause();
//                    Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                }
                //Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {
                //Log.d("onComplete", ">>");
            }
        });
    }

    private void loadUpComming(int page){

        useCaseHandler.execute(getUpcomingMovies, GetUpcomingMovies.Params.withPage(page), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper movieWrapper) {
                listener.onDataLoad(movieWrapper);

               // movieList.addAll(movieWrapper.results());
                //recyclerViewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable t) {
                listener.onDataError(t);
//                if (t.getCause() instanceof TMDBError) {
//                    TMDBError error = (TMDBError) t.getCause();
//                    Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                }
               // Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {
               // Log.d("onComplete", ">>");
            }
        });
    }

    private void loadTopRated(int page){

        useCaseHandler.execute(getTopRatedMovies, GetTopRatedMovies.Params.withPage(page), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper movieWrapper) {

                listener.onDataLoad(movieWrapper);
               // movieList.addAll(movieWrapper.results());
               // recyclerViewPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable t) {
                listener.onDataError(t);
//                if (t.getCause() instanceof TMDBError) {
//                    TMDBError error = (TMDBError) t.getCause();
//                    Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                }

            }

            @Override
            public void onComplete() {
               // Log.d("onComplete", ">>");
            }
        });
    }

    public void loadMyWatchList(){

        useCaseHandler.execute(getMyWatchList, GetMyWatchList.Params.justVoid(), new DisposableSubscriber<List<DomainObject>>() {
            @Override
            public void onNext(List<DomainObject> domainObjectList) {

                System.out.println("loadMyWatchList >" +domainObjectList);

                appConfig.setWatchList(domainObjectList);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void loadPopularPerson(int page){
        useCaseHandler.execute(getPopularPerson, GetPopularPerson.Params.with(page),

                new DisposableSubscriber<ResultWrapper>() {
                    @Override
                    public void onNext(ResultWrapper movieWrapper) {
                        listener.onDataLoad(movieWrapper);



                    }

                    @Override
                    public void onError(Throwable t) {
                        listener.onDataError(t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete loadFirstPage_NowPlaying");
                    }
                }


        );

    }


    private ObservableSource<? extends DomainObject> isInWatchList(DomainObject item) {
//        if(item instanceof Movie){
//            Movie mov = (Movie) item;
//            Movie movie1 = Observable.fromIterable(appConfig.getWatchList())
//                    .cast(Movie.class)
//                    .filter(movie -> (movie.id() == mov.id()))
//                    .firstElement()
//                    .blockingGet();
//
//
//            return Observable.just( mov.setInWatchList(true));
//
//
//        }
//
//        if(item instanceof TVShow){
//            TVShow tvShow = (TVShow) item;
//            TVShow tvShow1 = Observable.fromIterable(appConfig.getWatchList())
//                    .cast(TVShow.class)
//                    .filter(tv -> (tv.id() == tvShow.id()))
//                    .firstElement()
//                    .blockingGet();
//
//
//            return Observable.just( tvShow.setInWatchList(true));
//        }


      return  Observable.empty();

    }





    public List<DomainObject> prepareData(List<DomainObject> results){

        if( results.get(0) instanceof Movie){
            Observable.fromIterable(results)
                    .cast(Movie.class)
                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                    .distinct()

                    .filter(movie -> (movie.posterPath() != null))


                    .cast(DomainObject.class)
                    .toList().blockingGet();
        }
        if( results.get(0) instanceof TVShow){

            List<DomainObject> list = Observable.fromIterable(appConfig.getWatchList())
                    .filter(tv -> (tv instanceof TVShow))
                    .toList().blockingGet();

            return Observable.fromIterable(results)
                    .cast(TVShow.class)
                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                    .distinct()
                    .filter(movie -> (movie.posterPath() != null))
                    .flatMap(tvShow -> Observable.fromIterable(list)
                            .cast(TVShow.class)
                            .filter(tv -> (tv.id().equals(tvShow.id())))
                            .firstElement()
                            .map(tvShow2 -> tvShow.setInWatchList(true))
                            .defaultIfEmpty(tvShow)
                            .toObservable())
                    .map(tvShow -> {
                        System.out.println("##### "+tvShow.itemTitle()+" watch: "+tvShow.itemIsInWatchList());
                        return tvShow;
                    })
                    .cast(DomainObject.class)
                    .toList().blockingGet();
        }



        if( results.get(0) instanceof Person){
            return Observable.fromIterable(results)
                    .cast(Person.class)
                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                    .distinct()
                    .cast(DomainObject.class)
                    .toList().blockingGet();
        }
        return results;
    }

//    private CompletableSource isInWatchList(DomainObject item) {
//        if(item instanceof TVShow){
//            List<DomainObject> list = Observable.fromIterable(appConfig.getWatchList())
//                    .filter(tv -> (tv instanceof TVShow))
//                    .toList().blockingGet();
//            TVShow tvShow = (TVShow) item;
//            TVShow tvShow1 = Observable.fromIterable(appConfig.getWatchList())
//                    .cast(TVShow.class)
//                    .filter(tv -> (tv.id() == tvShow.id()))
//                    .firstElement()
//                    .blockingGet();
//
//
//            // return  tvShow.setInWatchList(true);
//        }
//
//        return Completable.create(e -> {
//
//
//            e.onComplete();
//
//
//        });


 //   }




}
