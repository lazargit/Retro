package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar.
 *
 *
 //        private String user_id;
 //        private String name;
 //        private String language;
 //        private String tmdb_guest_session;
 //        private String expires_at;
 //        private long last_update;
 */

 @AutoValue
 public abstract class User implements DomainObject {



        @Nullable
        public abstract String user_id();
        @Nullable
        public abstract String name();

        public abstract String language();
        @Nullable
        public abstract String tmdb_guest_session();
        @Nullable
        public abstract String tmdb_expires_at();

    public static User create(String language) {
        return builder()
                .language(language)
                .build();
    }
    private User.Builder getBuilder() {
        return builder()
                .user_id(user_id())
                .name(name())
                .language(language())
                .tmdb_guest_session(tmdb_guest_session())
                .tmdb_expires_at(tmdb_expires_at())
                ;
    }
    public User setSession(String guest_session, String expired_at) {

        return getBuilder()
                .tmdb_guest_session(guest_session)
                .tmdb_expires_at(expired_at)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder user_id(String user_id);

        public abstract Builder name(String name);

        public abstract Builder language(String language);

        public abstract Builder tmdb_guest_session(String tmdb_guest_session);

        public abstract Builder tmdb_expires_at(String tmdb_expires_at);

        public abstract User build();
    }
}