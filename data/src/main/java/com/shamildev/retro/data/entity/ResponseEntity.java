package com.shamildev.retro.data.entity;

import com.squareup.moshi.Json;


import java.util.List;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */



public class ResponseEntity implements Entity {


    final  int page;

//    @SerializedName("results")
    final  List<Result> results;

//    @SerializedName("dates")
//    public abstract DatesEntity dates();


    @Json(name = "total_pages")
    final  int totalPages;

    @Json(name = "total_results")
    final  int totalResults;


    public ResponseEntity(int page, List<Result> results, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Result> getResults() {
        return results;
    }


    private String listResult(List<Result> results){
           String str ="";
        for (Result item : results) {
            System.out.println(item);
            str=str+item.toString();
        }
            return str;

    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "page=" + page +
                ", results=" + listResult(results) +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }
}
