package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.util.DateUtil;


import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil  on 14.11.2017.
 */

@AutoValue
public abstract class Configuration implements DomainObject, DomainObjectStorable{



    public abstract String baseUrl();

    public abstract String secureBaseUrl();

    public abstract List<String> backdropSizes();

    public abstract List<String> logoSizes();

    public abstract List<String> posterSizes();

    public abstract List<String> profileSizes();

    public abstract List<String> stillSizes();

    @Nullable
    public abstract List<String> changeKeys();

    @Nullable
    public abstract Long lastUpdate();




    public static Builder builder() {
        return new AutoValue_Configuration.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder baseUrl(String baseUrl);

        public abstract Builder secureBaseUrl(String secureBaseUrl);

        public abstract Builder backdropSizes(List<String> backdropSizes);

        public abstract Builder logoSizes(List<String> logoSizes);

        public abstract Builder posterSizes(List<String> posterSizes);

        public abstract Builder profileSizes(List<String> profileSizes);

        public abstract Builder stillSizes(List<String> stillSizes);

        public abstract Builder changeKeys(List<String> changeKeys);

        public abstract Builder lastUpdate(Long lastUpdate);




        public abstract Configuration build();



    }



    @Override
    public String toString() {
        return baseUrl()+"\n#:"
                +secureBaseUrl()+"\n#:"
                +lastUpdate()+"--"+ DateUtil.convertToDateTime(lastUpdate())+"\n#:";


    }
}
