package com.shamildev.retro.data.cache.realm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.shamildev.retro.data.cache.realm.mapper.RealmMapperModule;
import com.shamildev.retro.data.entity.mapper.EntityMapperModule;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Shamil Lazar on 17.12.2017.
 */

@Module
public class RealmModule {

    @Provides
    @Singleton
    static RealmConfiguration provideRealmConfiguration(Application context,@Named("isDebug") boolean debug) {
        Realm.init(context);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        Log.d("provideCo", ">>>" + debug);
           if( debug) {

       // builder = builder.deleteRealmIfMigrationNeeded();
        // Realm.deleteRealm(builder.build());

         }
        return builder.build();
    }

    @Provides
    static Realm provideRealm(RealmConfiguration realmConfiguration) {
        Realm instance = Realm.getInstance(realmConfiguration);

        return instance;
    }


}
