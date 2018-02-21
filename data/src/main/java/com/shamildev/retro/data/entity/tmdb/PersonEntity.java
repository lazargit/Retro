package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;

/**
 * Created by Shamil Lazar on 15.02.2018.
 *   "birthday": "1974-01-30",
 "deathday": null,
 "id": 3894,
 "name": "Christian Bale",
 "also_known_as": [
 "Кристиан  Бейл ",
 "克里斯汀·貝爾",
 "คริสเตียน เบล",
 "クリスチャン・ベール",
 "크리스찬 베일",
 "كريستيان بيل"
 ],
 "gender": 2,
 "biography": "Christian Charles Philip Bale is a Welsh-born English actor. In addition to starring roles in big budget Hollywood films, he has long been heavily involved in films produced by independent producers and art houses. Bale first caught the public eye when he was cast in the starring role of Steven Spielberg's Empire of the Sun at the age of 13, playing an English boy who is separated from his parents and subsequently finds himself lost in a Japanese internment camp during World War II. Since then, he has portrayed a wide range of characters. Bale is especially noted for his cult following: the tenth anniversary issue of Entertainment Weekly hailed him as one of the \"Top 8 Most Powerful Cult Figures of the Past Decade\", citing his cult status on the Internet. EW called Bale one of the \"Most Creative People in Entertainment\" in anticipation of the release American Psycho (2000). The Guardian named Bale as one of the best actors never to have received an Academy Award nomination.\n\nBale was born in Haverfordwest, Pembrokeshire, Wales to parents of English descent. His father, David Bale, was an entrepreneur, commercial pilot, and talent manager and his mother, Jenny James, was a circus clown and performer. He is the youngest of four children. After leaving Wales in 1976, Bale spent his childhood in several countries, including England, Portugal and the United States. Settling for four years in Bournemouth and Henley-on-Thames, Bale was educated at Shiplake C of E Primary School, the independent Dolphin School in Berkshire, and at Bournemouth School. He participated actively in rugby union. Bale has described his childhood, with respect to his mother being in the circus, as \"interesting\". He recalled his first kiss was with an acrobat named Barta. As a child, he trained in ballet and guitar. His sister Louise's work in theatre also influenced his decision to become an actor. Bale's father was very supportive of his son's acting, resigning from his job as a commercial pilot to travel and manage Bale's burgeoning career. Bale's first foray into acting was a commercial for the fabric softener Lenor in 1982, when he was eight years old. He appeared in a Pac-Man cereal commercial playing a child rock star a year later and in 1984 made his stage debut in The Nerd, opposite Rowan Atkinson.\n\nOn 29 January, 2000, Bale married Sandra \"Sibi\" Blažić (born 1970), a one-time model, make-up artist and personal assistant to Winona Ryder; the couple have a daughter, Emmeline, who was born on 27 March 2005, in Santa Monica, California. Since 1992, Bale has resided in Los Angeles.",
 "popularity": 6.007134,
 "place_of_birth": "Haverfordwest, Pembrokeshire, Wales, UK",
 "profile_path": "/2ocrTd8ChuUyJOzMuD4PsQw8eCB.jpg",
 "adult": false,
 "imdb_id": "nm0000288",
 "homepage": null
 */

public class PersonEntity implements Serializable, Entity {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("deathday")
    @Expose
    private String deathday;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("biography")
    @Expose
    private String biography;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public Float getPopularity() {
        return popularity;
    }

    public String getBiography() {
        return biography;
    }
}
