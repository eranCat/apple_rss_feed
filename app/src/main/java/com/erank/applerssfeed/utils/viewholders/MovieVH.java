package com.erank.applerssfeed.utils.viewholders;

import android.view.ViewGroup;
import android.widget.TextView;

import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.Genre;
import com.erank.applerssfeed.utils.interfaces.OnDataCallback;

import java.util.StringJoiner;

public class MovieVH extends DataVH {

    private TextView genresTv;

    public MovieVH(ViewGroup parent) {
        super(parent,R.layout.movie_item);

        genresTv = itemView.findViewById(R.id.genres_tv);
    }

    @Override
    public void fill(Data data, OnDataCallback callback) {
        super.fill(data, callback);

        StringJoiner joiner = new StringJoiner(", ");
        for (Genre genre : data.getGenres()) {
            joiner.add(genre.name);
        }

        genresTv.setText(joiner.toString());
    }
}
