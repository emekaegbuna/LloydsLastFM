package com.example.wiprotest.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wiprotest.R;
import com.example.wiprotest.model.AlbumResultsPojo;
import com.example.wiprotest.util.CustomListener;

public class CustomViewHolder extends RecyclerView.ViewHolder{

    ImageView ivAlbumImage;
    TextView tvAlbumName, tvAlbumArtist;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        ivAlbumImage = itemView.findViewById(R.id.iv_album_image);
        tvAlbumArtist = itemView.findViewById(R.id.tv_album_artist);
        tvAlbumName = itemView.findViewById(R.id.tv_album_name);
    }


    public void onBindViewHolder(final AlbumResultsPojo item,
                                 final CustomListener listener,
                                 final int position) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(item, position);
            }
        });
    }
}
