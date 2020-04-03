package com.erank.applerssfeed.utils;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.utils.interfaces.OnDataCallback;
import com.erank.applerssfeed.utils.viewholders.DataVH;
import com.erank.applerssfeed.utils.viewholders.MovieVH;

import java.util.List;

public class DataAdapter extends ListAdapter<Data, DataVH> {

    private OnDataCallback callback;

    public DataAdapter(List<Data> allData, @NonNull OnDataCallback callback) {
        super(DataItemCallback.create());
        submitList(allData);
        this.callback = callback;
    }

    @NonNull
    @Override
    public DataVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MediaType type = MediaType.values()[viewType];
        switch (type) {
// TODO: 03/04/2020 add those ...
//            case MUSIC:return new MusicVH(parent);
//            case SHOWS:return new ShowVH(parent);
//            case APPS:return new AppVH(parent);
//            case PODCASTS:return new PodcastVH(parent);

            case MOVIES:
                return new MovieVH(parent);
            default:
                return new DataVH(parent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().ordinal();
    }

    @Override
    public void onBindViewHolder(@NonNull DataVH holder, int i) {
        holder.fill(getItem(i), callback);
    }

    public void setList(List<Data> list) {
        submitList(list);
        notifyDataSetChanged();
    }
}
