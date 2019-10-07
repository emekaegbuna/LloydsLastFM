package com.example.wiprotest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumMatchesPojo {

    @SerializedName("album")
    @Expose
    public List<AlbumPojo> album = null;

}
