package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 02.11.2017.
 */

public class ResponseEntity implements Serializable , Entity {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("total_results")
        @Expose
        private Integer totalResults;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;
        @SerializedName("results")
        @Expose
        private List<Result> results = null;
        private final static long serialVersionUID = 463478823370456371L;




    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }





}
