package com.shamildev.retro.data.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by Schamil  on 02.11.2017.
 */

public class Response implements Serializable {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("total_results")
        @Expose
        private Integer totalResults;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;

    public Response(Integer page, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

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






}
