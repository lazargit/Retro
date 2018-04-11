package com.shamildev.retro.data.cache.realm.mapper;

import com.shamildev.retro.data.cache.realm.models.WatchListRealm;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.util.Constants;

import javax.inject.Inject;

import dagger.Reusable;
import io.realm.RealmList;

/**
 * Created by Shamil Lazar on 31.12.2017.
 */

@Reusable
final class TvShowRealmMapper implements RealmMapper<TVShow, WatchListRealm> {

    @Inject
    RealmListMapper realmListMapper;

    @Inject
    TvShowRealmMapper() {

    }


    @Override
    public WatchListRealm map(TVShow item) {

        WatchListRealm movieRealm = new WatchListRealm();
                    movieRealm.setId(item.id());
                    movieRealm.setMedia_type(Constants.MEDIA_TYPE.TV.toString());
                    movieRealm.setPopularity(item.popularity());
                    movieRealm.setTitle(item.name());
                    movieRealm.setOverview(item.overview());
                    movieRealm.setOriginal_title(item.originalName());
                    movieRealm.setRelease_date(item.firstAirDate());
                    movieRealm.setPoster_path(item.posterPath());
                    movieRealm.setBackdrop_path(item.backdropPath());
                    movieRealm.setOriginal_language(item.originalLanguage());
                    movieRealm.setVote_average(item.voteAverage());
                    RealmList<Integer> integers = new RealmList<>();
                    integers.addAll(item.genreIds());
                    movieRealm.setGenre_ids(integers);


        return movieRealm;
    }

    @Override
    public TVShow map(WatchListRealm entity) {




        return   TVShow.create(entity.getId(),
                entity.getTitle(),
                entity.getOriginal_title(),
                entity.getOverview(),
                entity.getPoster_path(),
                entity.getBackdrop_path(),
                entity.getOriginal_language(),
                entity.getPopularity(),
                0,
                entity.getVote_average(),
                entity.getRelease_date(),
               null,
                RealmListMapper.mapToList(entity.getGenre_ids()));






    }


}
