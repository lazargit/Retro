package com.shamildev.retro.data.cache.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Schamil Mischijew on 03.11.2017.
 */

public class MovieRealm extends RealmObject {



        @PrimaryKey
        private long id;
        private String media_type;
        private String title;
        private String original_title;
        private String original_language;
        private String poster_path;
        private String backdrop_path;
        private Boolean video;
        private Float vote_average;
        private Float popularity;
        private Boolean adult;
        private String overview;
        private String release_date;
        private RealmList<Integer> genre_ids;
        private long last_update;

        public MovieRealm() {



        }



        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }


        public String getOriginal_title() {
                return original_title;
        }

        public void setOriginal_title(String original_title) {
                this.original_title = original_title;
        }

        public String getOriginal_language() {
                return original_language;
        }

        public void setOriginal_language(String original_language) {
                this.original_language = original_language;
        }

        public String getPoster_path() {
                return poster_path;
        }

        public void setPoster_path(String poster_path) {
                this.poster_path = poster_path;
        }

        public String getBackdrop_path() {
                return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
                this.backdrop_path = backdrop_path;
        }

        public Boolean getVideo() {
                return video;
        }

        public void setVideo(Boolean video) {
                this.video = video;
        }

        public Float getVote_average() {
                return vote_average;
        }

        public void setVote_average(Float vote_average) {
                this.vote_average = vote_average;
        }

        public String getMedia_type() {
                return media_type;
        }

        public void setMedia_type(String media_type) {
                this.media_type = media_type;
        }

        public Float getPopularity() {
                return popularity;
        }

        public void setPopularity(Float popularity) {
                this.popularity = popularity;
        }

        public Boolean getAdult() {
                return adult;
        }

        public void setAdult(Boolean adult) {
                this.adult = adult;
        }

        public String getOverview() {
                return overview;
        }

        public void setOverview(String overview) {
                this.overview = overview;
        }

        public String getRelease_date() {
                return release_date;
        }

        public void setRelease_date(String release_date) {
                this.release_date = release_date;
        }

        public RealmList<Integer> getGenre_ids() {
                return genre_ids;
        }

        public void setGenre_ids(RealmList<Integer> genre_ids) {
                this.genre_ids = genre_ids;
        }

        public long getLast_update() {
                return last_update;
        }

        public void setLast_update(long last_update) {
                this.last_update = last_update;
        }
}
/*vote_count	6232
        id	210577
        video	false
        vote_average	7.9
        title	"Gone Girl - Das perfekte Opfer"
        popularity	218.118227
        poster_path	"/uB7a06983wECVPufB84LQXzovRn.jpg"
        original_language	"en"
        original_title	"Gone Girl"
        genre_ids
        0	9648
        1	53
        2	18
        backdrop_path	"/bt6DhdALyhf90gReozoQ0y3R3vZ.jpg"
        adult	false
        overview	"Ein warmer Sommermorgen in Missouri: Nick und Amy wollten heute eigentlich ihren fünften Hochzeitstag feiern, doch Amy ist plötzlich verschwunden. Als sie nicht wieder auftaucht, gerät Nick ins Visier der Polizei. Der Verlassene besteht jedoch auf seine Unschuld, verstrickt sich aber immer mehr in ein Netz aus Lügen und Verrat. Nach und nach tauchen Indizien auf, die darauf hindeuten, dass Amy Angst vor ihrem Mann hatte. Doch auch Amys Weste ist nicht so rein wie angenommen. Durch den Fund ihres Tagebuchs kommen dunkle Dinge ans Licht, die niemand jemals von der vermeintlich perfekten Frau erwartet hätte. Ob Amy überhaupt noch am Leben ist, bleibt weiterhin unklar…"
        release_date	"2014-10-01"*/