package com.shamildev.retro.data.cache.realm.mapper;

import com.shamildev.retro.data.cache.realm.models.ConfigurationRealm;
import com.shamildev.retro.data.cache.realm.models.MovieRealm;
import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.realm.RealmList;

/**
 * Created by Shamil Lazar on 31.12.2017.
 */

@Reusable
final class ConfigurationRealmMapper implements RealmMapper<Configuration, TMDbConfigurationRealm> {


    @Inject
    ConfigurationRealmMapper() {

    }


    @Override
    @SuppressWarnings("unchecked")
    public TMDbConfigurationRealm map(Configuration model) {

        TMDbConfigurationRealm configurationRealm = new TMDbConfigurationRealm();

        configurationRealm.setBaseUrl(model.baseUrl());
        configurationRealm.setSecureBaseUrl(model.secureBaseUrl());
        configurationRealm.setBackdropSizes(RealmListMapper.listToRealmList(model.backdropSizes()));
        configurationRealm.setChangeKeys(RealmListMapper.listToRealmList(model.changeKeys()));
        configurationRealm.setLogoSizes(RealmListMapper.listToRealmList(model.logoSizes()));
        configurationRealm.setPosterSizes(RealmListMapper.listToRealmList(model.posterSizes()));
        configurationRealm.setProfileSizes(RealmListMapper.listToRealmList(model.posterSizes()));
        configurationRealm.setStillSizes(RealmListMapper.listToRealmList(model.stillSizes()));
        configurationRealm.setLast_update(model.lastUpdate());


        return configurationRealm;
    }

    @Override
    public Configuration map(TMDbConfigurationRealm entity) {

        System.out.println("ConfigurationRealmMapper" +entity.getBackdropSizes());


        return Configuration.builder()
                .baseUrl(entity.getBaseUrl())
                .secureBaseUrl(entity.getSecureBaseUrl())
                .backdropSizes( RealmListMapper.mapToStringList(entity.getBackdropSizes()))
                .changeKeys( RealmListMapper.mapToStringList(entity.getChangeKeys()))
                .logoSizes( RealmListMapper.mapToStringList(entity.getLogoSizes()))
                .posterSizes( RealmListMapper.mapToStringList(entity.getPosterSizes()))
                .profileSizes( RealmListMapper.mapToStringList(entity.getProfileSizes()))
                .stillSizes( RealmListMapper.mapToStringList(entity.getStillSizes()))
                .lastUpdate(entity.getLast_update())
                .build();
    }


}
