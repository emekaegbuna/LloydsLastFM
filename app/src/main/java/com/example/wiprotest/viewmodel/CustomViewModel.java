package com.example.wiprotest.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wiprotest.model.AlbumResultsPojo;
import com.example.wiprotest.model.ApiInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CustomViewModel extends ViewModel {

    private MutableLiveData<AlbumResultsPojo> albumList;

    public LiveData<AlbumResultsPojo> getAlbums(String albumName) {
        albumList = new MutableLiveData<>();
        loadAlbums(albumName);
        return albumList;
    }

    private void loadAlbums(String albumName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.geAlbumData("album.search", albumName, "4cdd5fefbd9a1a33401dc56104c05375", "json").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AlbumResultsPojo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AlbumResultsPojo albumResultsPojo) {
                albumList.setValue(albumResultsPojo);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
