package com.erank.applerssfeed.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.utils.interfaces.DataFetchedCallback;
import com.erank.applerssfeed.utils.room.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.erank.applerssfeed.utils.ShortStuff.toast;

public class SplashActivity extends AppCompatActivity  {

    private List<MediaType> mediaTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mediaTypes = new ArrayList<>();
        Collections.addAll(mediaTypes,MediaType.values());
        mediaTypes.forEach(this::getData);
    }

    private void getData(MediaType media) {
        DataFetchedCallback callback = new DataFetchedCallback() {
            @Override
            public void onDataFetched(List<Data> list) { update(media); }

            @Override
            public void onErrorFetching(Exception e) { showError(e); }
        };
        DataSource.getData(this, media, callback);
    }

    public void update(MediaType media) {
        synchronized (this) {
            mediaTypes.remove(media);
            if (mediaTypes.isEmpty()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }

    public void showError(Exception e) {
        toast(this, e.getLocalizedMessage());

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        TextView tv = findViewById(R.id.loading_tv);
        tv.setText("error loading");
        tv.setTextColor(Color.RED);
    }

}
