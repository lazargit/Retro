package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

@AutoValue
public abstract class ImageModel implements DomainObject {

    public abstract Double aspectRatio();
    public abstract String filePath();
    public abstract Integer height();
    @Nullable
    public abstract String iso6391();
    public abstract Double voteAverage();
    public abstract Integer voteCount();
    public abstract Integer width();




    public static Builder builder() {
        return new AutoValue_ImageModel.Builder();
    }



    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder aspectRatio(Double aspectRatio);

        public abstract Builder filePath(String filePath);

        public abstract Builder height(Integer height);

        public abstract Builder iso6391(String iso6391);

        public abstract Builder voteAverage(Double voteAverage);

        public abstract Builder voteCount(Integer voteCount);

        public abstract Builder width(Integer width);

        public abstract ImageModel build();
    }


}
