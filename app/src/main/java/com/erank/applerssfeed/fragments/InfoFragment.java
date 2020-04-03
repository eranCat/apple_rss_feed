package com.erank.applerssfeed.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

import static com.google.android.material.R.id.design_bottom_sheet;

public class InfoFragment extends BottomSheetDialogFragment {

    private ImageView imageView;
    private TextView authorTv,dateTv,genreTv,nameTV;
    private Button openWebBtn;

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
        DataSource.getData(id,this::fill);

        imageView = view.findViewById(R.id.imageView);
        authorTv = view.findViewById(R.id.author_tv);
        dateTv = view.findViewById(R.id.release_date_tv);
        genreTv = view.findViewById(R.id.genres_tv);
        nameTV = view.findViewById(R.id.name_tv);
        openWebBtn = view.findViewById(R.id.open_url_btn);

        makeDialogExpanded(view);
    }

    private void makeDialogExpanded( View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                if (dialog == null) return;

                FrameLayout bottomSheet = dialog.findViewById(design_bottom_sheet);
                if (bottomSheet == null) return;

                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                behavior.setPeekHeight(0); // Remove this line to hide a dark background if you manually hide the dialog.
            }
        });
    }

    private void fill(Data data) {
        Glide.with(this)
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

        openWebBtn.setOnClickListener(v -> {
            Uri parse = Uri.parse(data.getUrl());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, parse);
            startActivity(browserIntent);
        });
    }

}
