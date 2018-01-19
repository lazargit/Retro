package com.shamildev.retro.data.cache.realm.mapper;

import com.shamildev.retro.data.cache.realm.models.GenreRealm;
import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Created by Shamil Lazar on 31.12.2017.
 */

@Reusable
final class GenreRealmMapper implements RealmMapper<Genre, GenreRealm> {


    @Inject
    GenreRealmMapper() {

    }


    @Override
    public GenreRealm map(Genre model) {

        GenreRealm genreRealm = new GenreRealm();
        genreRealm.setKey(model.id()+""+model.type().hashCode()+""+model.language().hashCode());
        genreRealm.setGenreId(model.id());
        genreRealm.setName(model.name());
        genreRealm.setLanguage(model.language());
        genreRealm.setMediaType(model.type());
        return genreRealm;

    }

    @Override
    public Genre map(GenreRealm entity) {


      return  Genre.builder()
                .id(entity.getGenreId())
                .name(entity.getName())
                .language(entity.getLanguage())
                .type(entity.getMediaType())
                .lastUpdate(entity.getLast_update())
                .build();
    }




}
