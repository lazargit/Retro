package com.shamildev.retro.domain.params;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shamil Lazar on 09.02.2018.
 */

public class Discover {
    private static final String SORT_BY = "sort_by";
    private static final String WITH_PEOPLE = "with_people";
    private static final String WITH_GENRES = "with_genres";
    private static final String WITH_CAST = "with_cast";
    private static final String YEAR = "year";

    private static final String PRIMARY_RELEASE_YEAR = "primary_release_year";
    private static final String PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
    private static final String PRIMARY_RELEASE_DATE_LTE = "primary_release_date.lte";
    private final Map<String,Object>  map;


    // all fields are final: immutable object



    String POPULARITYASC ="popularity.asc";
    String POPULARITYDESC ="popularity.desc";
    String RELEASE_DATEASC = "release_date.asc";
    String RELEASE_DATEDESC = "release_date.desc";


    public enum SORT_TYPE {

        POPULARITY_ASC {
            public String toString() {
                return "popularity.asc";
            }
        },

        POPULARITY_DESC {
            public String toString() {
                return "popularity.desc";
            }
        },
        RELEASEDATE_ASC {
            public String toString() {
                return "release_date.asc";
            }
        },

        RELEASEDATE_DESC {
            public String toString() {
                return "release_date.desc";
            }
        }




    }


    private StringBuilder stringBuilder;


    private Discover(Builder builder) {
        // private Constructor can only be called from Builder

        map = new HashMap<>();
        stringBuilder = new StringBuilder();
        trim(builder.withPeople,WITH_PEOPLE);
        trim(builder.withGenres,WITH_GENRES);
        trim(builder.year,YEAR);
        trim(builder.withCast, WITH_CAST);

        trim(builder.primary_release_year, PRIMARY_RELEASE_YEAR);
        trim(builder.primary_release_date_gte, PRIMARY_RELEASE_DATE_GTE);
        trim(builder.primary_release_date_lte, PRIMARY_RELEASE_DATE_LTE);

        trim(builder.sortBy, SORT_BY);




    }



    private void trim(String str, String prefix){

        if(str!=null) {
            if (str.length() > 0) {
                if (str.indexOf(",") == 0 || str.indexOf("|") == 0) {
                    str = str.substring(1);
                    System.out.println(prefix+"------"+str);
                }
                String nStr = prefix + str;
                stringBuilder.append(nStr);
                map.put(prefix,str);

            }

        }

    }

    public String getPath() {
        return stringBuilder.toString();
    }
    public Map<String, Object> getMap() {
        return map;
    }



    public static class Builder {
        // mandatory parameter


        // optional
        private SORT_TYPE sorttype;
        private String withPeople = "";
        private String sortBy = "";
        private String withGenres="";
        private String withCast="";

        private String year;
        private String primary_release_date_gte;
        private String primary_release_date_lte;
        private String primary_release_year;

        public Builder() {

        }
        public Builder with_people(Integer id) {

            this.withPeople += ","+Integer.toString(id);

            return this;
        }
        public Builder or_with_people(int id) {

            this.withPeople += "|"+Integer.toString(id);
            return this;
        }
        public Builder withGenres(int id) {

            this.withGenres += ","+Integer.toString(id);
            return this;
        }
        public Builder or_withGenres(int id) {

            this.withGenres += "|"+Integer.toString(id);
            return this;
        }

        public Builder withCast(int id) {

            this.withCast += ","+Integer.toString(id);
            return this;
        }
        public Builder or_withCast(int id) {

            this.withCast += "|"+Integer.toString(id);
            return this;
        }

        public Builder year(int id) {

            this.year = Integer.toString(id);
            return this;
        }

        public Builder primaryReleaseYear(String date) {

            this.primary_release_year = date;
            return this;
        }

        public Builder primaryReleaseDateGte(String date) {

            this.primary_release_date_gte = date;
            return this;
        }
        public Builder primaryReleaseDateLte(String date) {

            this.primary_release_date_lte = date;
            return this;
        }



        public Builder sortBy(SORT_TYPE sorttype) {

            this.sortBy = sorttype.toString();
            return this;
        }

        public Discover build() {
            return new Discover(this);
        }
    }
}