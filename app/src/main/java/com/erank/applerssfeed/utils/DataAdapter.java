package com.erank.applerssfeed.utils;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;

import java.util.List;

public class DataAdapter extends ListAdapter<Data,DataAdapter.DataVH> {

    private OnDataCallback callback;

    public DataAdapter(List<Data> allData, @NonNull OnDataCallback callback) {
        super(DataItemCallback.create());
        submitList(allData);
        this.callback = callback;
    }

    @NonNull
    @Override
    public DataVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataVH(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull DataVH holder, int position) {
        Data data = getItem(position);
        holder.fill(data);
        holder.itemView.setOnClickListener(v -> callback.onDataClickedCallback(data));
    }

    public void setList(List<Data> list) {
        submitList(list);
        notifyDataSetChanged();
    }

    public interface OnDataCallback {
        void onDataClickedCallback(Data data);
    }

    static class DataVH extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;

        DataVH(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.app_item, parent, false));

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title_tv);
        }

        void fill(Data data) {
            Glide.with(itemView)
                    .load(data.getPhotoUrl())
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .transform(new RoundedCorners(16))
                    .into(imageView);

            title.setText(data.getName());
        }
    }
}
