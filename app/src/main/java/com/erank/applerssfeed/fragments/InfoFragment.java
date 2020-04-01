package com.erank.applerssfeed.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.Genre;
import com.erank.applerssfeed.utils.room.DataSource;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringJoiner;

public class InfoFragment extends BottomSheetDialogFragment {

    public static InfoFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String id = getArguments().getString("id");
        Data data = DataSource.getData(id);

        if (data == null) return;

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView authorTv = view.findViewById(R.id.author_tv);
        TextView dateTv = view.findViewById(R.id.release_date_tv);
        TextView genreTv = view.findViewById(R.id.genres_tv);
        TextView nameTV = view.findViewById(R.id.name_tv);

        Glide.with(view)
                .load(data.getPhotoUrl())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .transform(new RoundedCorners(16))
                .into(imageView);

        nameTV.setText(data.getName());
        authorTv.setText(data.getArtistName());

        StringJoiner joiner = new StringJoiner(", ");
        for (Genre genre : data.getGenres()) {
            joiner.add(genre.name);
        }

        genreTv.setText(joiner.toString());

        Date releaseDate = data.getReleaseDate();
        if (releaseDate != null) {
            DateFormat format = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM);
            dateTv.setText(format.format(releaseDate));
        }

        view.findViewById(R.id.open_url_btn).setOnClickListener(v -> {
            Uri parse = Uri.parse(data.getUrl());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, parse);
            startActivity(browserIntent);
        });

    }


}
