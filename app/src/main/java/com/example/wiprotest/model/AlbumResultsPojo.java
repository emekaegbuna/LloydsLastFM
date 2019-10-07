package com.example.wiprotest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumResultsPojo {

    @SerializedName("results")
    @Expose
    public ResultsPojo results;

}
