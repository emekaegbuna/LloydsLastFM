package com.example.wiprotest.model;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    // http://ws.audioscrobbler.com/2.0/?method=album.search&album=believe&api_key=4cdd5fefbd9a1a33401dc56104c05375&format=json
    @GET("2.0/")
    Observable<AlbumResultsPojo> geAlbumData(
            @Query("method") String method,
            @Query("album") String album,
            @Query("api_key") String apiKey,
            @Query("format") String format);

    //http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=4cdd5fefbd9a1a33401dc56104c05375&artist=Cher&album=Believe&format=json
}
