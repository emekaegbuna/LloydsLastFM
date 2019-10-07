package com.example.wiprotest.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wiprotest.R;
import com.example.wiprotest.model.AlbumResultsPojo;
import com.example.wiprotest.util.CustomListener;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    AlbumResultsPojo dataSet;
    Context context;
    CustomListener listener;

    public CustomAdapter(Context context, AlbumResultsPojo dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    public void setListener(CustomListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvAlbumName.setText(dataSet.results.albummatches.album.get(position).name);
        holder.tvAlbumArtist.setText(dataSet.results.albummatches.album.get(position).artist);
        Glide.with(context).load(dataSet.results.albummatches.album.get(position).image.get(1).text).into(holder.ivAlbumImage);
        holder.onBindViewHolder(dataSet, listener, position);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.results.albummatches.album.size() : 0;
    }
}
