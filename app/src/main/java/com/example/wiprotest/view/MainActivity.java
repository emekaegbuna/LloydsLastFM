package com.example.wiprotest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.wiprotest.R;
import com.example.wiprotest.model.AlbumMatchesPojo;
import com.example.wiprotest.model.AlbumResultsPojo;
import com.example.wiprotest.model.ResultsPojo;
import com.example.wiprotest.util.CustomListener;
import com.example.wiprotest.viewmodel.CustomAdapter;
import com.example.wiprotest.viewmodel.CustomViewModel;

public class MainActivity extends AppCompatActivity implements CustomListener {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private CustomViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = ViewModelProviders.of(this).get(CustomViewModel.class);
        model.getAlbums("believe").observe(this, new Observer<AlbumResultsPojo>() {
            @Override
            public void onChanged(AlbumResultsPojo albumResultsPojo) {
                adapter = new CustomAdapter(MainActivity.this, albumResultsPojo);
                adapter.setListener(MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Search For Albums");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                model.getAlbums(query).observe(MainActivity.this, new Observer<AlbumResultsPojo>() {
                    @Override
                    public void onChanged(AlbumResultsPojo albumResultsPojo) {
                        adapter = new CustomAdapter(MainActivity.this, albumResultsPojo);
                        adapter.setListener(MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
        return true;
    }

    @Override
    public void onClick(AlbumResultsPojo item, int position) {
        Intent intent = new Intent(this, AlbumDetailsActivity.class);
        intent.putExtra("image", item.results.albummatches.album.get(position).image.get(3).text);
        intent.putExtra("album", item.results.albummatches.album.get(position).name);
        intent.putExtra("artist", item.results.albummatches.album.get(position).artist);
        intent.putExtra("url", item.results.albummatches.album.get(position).url);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}
