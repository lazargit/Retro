package com.shamildev.retro.data.cache.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Schamil Mischijew on 17.11.2017.
 */

public class ConfigurationRealm extends RealmObject {

    @PrimaryKey
    private String user;

    private TMDbConfigurationRealm configRealmRealm;
    private RealmList<String> changeKeys = new RealmList<>();



    private long create_date;
    private long last_update;


    public ConfigurationRealm() {

    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public TMDbConfigurationRealm getConfigMdbRealm() {
        return configRealmRealm;
    }
//
    public void setConfigRealmRealm(TMDbConfigurationRealm configRealmRealmObject) {
        this.configRealmRealm = configRealmRealmObject;
    }


    public long getCreate_date() {
        return create_date;
    }

    public void setCreate_date(long create_date) {
        this.create_date = create_date;
    }
    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }
    public long getLast_update() {
        return last_update;
    }

    public RealmList<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(RealmList<String> changeKeys) {
        this.changeKeys.addAll(changeKeys);
    }


    @Override
    public String toString() {
        return "ConfigurationRealm{" +
                "user='" + user + '\'' +
                ", changeKeys=" + changeKeys +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                '}';
    }
}
