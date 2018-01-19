package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 *
 * credit_id:52fe421fc3a36847f8005cbb
 department:Directing
 gender:2
 id:525
 job:Director
 name:Christopher Nolan
 profile_path:/7OGmfDF4VHLLgbjxuEwTj3ga0uQ.jpg
 */

public class CrewEntity implements Serializable , Entity
{



    @SerializedName("credit_id")
    @Expose
    private String creditId;


    @SerializedName("department")
    @Expose
    private String department;

    @SerializedName("gender")
    @Expose
    private Integer gender;

    @SerializedName("job")
    @Expose
    private String job;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("profile_path")
    @Expose
    private String profilePath;




    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}