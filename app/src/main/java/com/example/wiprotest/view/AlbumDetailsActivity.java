package com.example.wiprotest.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.wiprotest.R;

public class AlbumDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView ivAlbumDetailImage = findViewById(R.id.iv_album_detail_image);
        String albumImage = getIntent().getStringExtra("image");
        Glide.with(this).load(albumImage).into(ivAlbumDetailImage);
        TextView tvAlbumDetailName = findViewById(R.id.tv_album_detailed_name);
        tvAlbumDetailName.setText("Album Name: " + getIntent().getStringExtra("album"));
        TextView tvAlbumDetailArtist = findViewById(R.id.tv_album_detailed_artist);
        tvAlbumDetailArtist.setText("Album Artist: " + getIntent().getStringExtra("artist"));
        TextView tvAlbumDetailedUrl = findViewById(R.id.tv_album_detailed_url);
        SpannableString contentAddress = new SpannableString("Album Url: " + getIntent().getStringExtra("url"));
        contentAddress.setSpan(new UnderlineSpan(), 0, contentAddress.length(), 0);
        tvAlbumDetailedUrl.setText(contentAddress);
        tvAlbumDetailedUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl = Uri.parse(getIntent().getStringExtra("url"));
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }
}
