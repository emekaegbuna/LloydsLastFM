package com.example.wiprotest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumPojo {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("artist")
    @Expose
    public String artist;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("image")
    @Expose
    public List<AlbumImagePojo> image = null;
    @SerializedName("streamable")
    @Expose
    public String streamable;
    @SerializedName("mbid")
    @Expose
    public String mbid;

}