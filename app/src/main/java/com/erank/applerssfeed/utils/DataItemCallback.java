package com.erank.applerssfeed.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.erank.applerssfeed.models.Data;

public class DataItemCallback extends DiffUtil.ItemCallback<Data> {

    public static AsyncDifferConfig<Data> create() {
        return new AsyncDifferConfig.Builder<>(new DataItemCallback()).build();
    }

    @Override
    public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
        String id = oldItem.getId();
        String id1 = newItem.getId();
        return id.equals(id1);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
        return oldItem.equals(newItem);
    }
}
