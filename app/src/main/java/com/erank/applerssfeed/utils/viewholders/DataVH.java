package com.erank.applerssfeed.utils.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.utils.interfaces.OnDataCallback;

public class DataVH extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView title;

    public DataVH(ViewGroup parent, @LayoutRes int layout) {
        super(inflate(parent, layout));

        imageView = itemView.findViewById(R.id.imageView);
        title = itemView.findViewById(R.id.title_tv);
    }

    public DataVH(ViewGroup parent) {
        this(parent, R.layout.app_item);
    }

    private static View inflate(ViewGroup parent, @LayoutRes int layout) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(layout, parent, false);
    }

    public void fill(Data data, OnDataCallback callback) {
        Glide.with(itemView)
                .load(data.getPhotoUrl())
                .placeholder(R.drawable.ic_image_black_24dp)
                .transform(new RoundedCorners(16))
                .into(imageView);

        title.setText(data.getName());

        itemView.setOnClickListener(v -> {
            Animation animation = AnimationUtils
                    .loadAnimation(v.getContext(), R.anim.pop_anim);

            v.startAnimation(animation);

            if (callback != null) callback.onDataClickedCallback(data);
        });
    }
}
