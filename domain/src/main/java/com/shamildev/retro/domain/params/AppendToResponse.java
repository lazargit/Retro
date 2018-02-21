package com.shamildev.retro.domain.params;




import com.shamildev.retro.domain.util.Constants;

import java.util.ArrayList;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

public  class AppendToResponse implements ParamsBasic {


    public AppendToResponse(Builder builder) {


    }


    public static class Builder
    {

        ArrayList<String> stringBuilder;

        // videos,images,trailers,similar_movies,release_dates,changes,credits,reviews,keywords,lists,translations,recommendations
        public Builder() {

           stringBuilder = new ArrayList<>();

        }
        public Builder withImages() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.IMAGES.toString());
            return this;
        }
        public AppendToResponse.Builder withVideos() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.VIDEOS.toString());
            return this;

        }
        public AppendToResponse.Builder withTrailers() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.TRAILERS.toString());
            return this;
        }
        public AppendToResponse.Builder withSimilarMovies() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.SIMILAR_MOVIES.toString());
            return this;
        }
        public AppendToResponse.Builder withReleaseDates() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.RELEASE_DATES.toString());
            return this;
        }
        public AppendToResponse.Builder withChanges() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.CHANGES.toString());
            return this;
        }
        public AppendToResponse.Builder withCredits() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.CREDITS.toString());
            return this;
        }
        public AppendToResponse.Builder withReviews() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.REVIEWS.toString());
            return this;
        }
        public AppendToResponse.Builder withKeywords() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.KEYWORDS.toString());
            return this;
        }
        public AppendToResponse.Builder withLists() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.LISTS.toString());
            return this;
        }
        public AppendToResponse.Builder withTranslations() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.TRANSLATION.toString());
            return this;
        }
        public AppendToResponse.Builder withRecommendations() {
            stringBuilder.add(Constants.APPEND_TO_RESPONSE.RECOMMENDATIONS.toString());
            return this;
        }

        public String build() {
                 String str = "";
                 for (String s : stringBuilder) {
                            str =  str+ (s+",");

                 }
            return str.substring(0,(str.length()-1));
        }
    }



}
