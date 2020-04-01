package com.erank.applerssfeed.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.utils.DataFetchedCallback;
import com.erank.applerssfeed.utils.Media;
import com.erank.applerssfeed.utils.room.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private List<Media> medias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        medias = new ArrayList<>(Arrays.asList(Media.values()));
        medias.forEach(this::downloadAndUpdate);
    }

    private void downloadAndUpdate(Media media) {
        DataSource.getData(this, media, new DataFetchedCallback() {
            @Override
            public void onDataFetched(List<Data> list, Media type) {
                medias.remove(type);
                checkIfDone();
            }

            @Override
            public void onErrorFetching(Exception e) {
                Toast.makeText(SplashActivity.this,
                        e.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();
                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                TextView tv = findViewById(R.id.loading_tv);
                tv.setText("error loading");
                tv.setTextColor(Color.RED);
            }
        });
    }

    private void checkIfDone() {

        if (!medias.isEmpty())
            return;

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
