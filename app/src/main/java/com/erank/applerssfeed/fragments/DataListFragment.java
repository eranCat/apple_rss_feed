package com.erank.applerssfeed.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erank.applerssfeed.R;
import com.erank.applerssfeed.models.Data;
import com.erank.applerssfeed.models.MediaType;
import com.erank.applerssfeed.models.SortType;
import com.erank.applerssfeed.utils.DataAdapter;
import com.erank.applerssfeed.utils.interfaces.Filterable;
import com.erank.applerssfeed.utils.interfaces.OnDataCallback;
import com.erank.applerssfeed.utils.interfaces.Sortable;
import com.erank.applerssfeed.utils.room.DataSource;


public class DataListFragment extends Fragment
        implements OnDataCallback, Sortable, Filterable {

    private MediaType mediaType;
    private DataAdapter adapter;

    public static DataListFragment newInstance(MediaType type) {

        Bundle args = new Bundle();
        args.putSerializable("type", type);
        DataListFragment fragment = new DataListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.recycler);

        mediaType = (MediaType) getArguments().getSerializable("type");
        DataSource.getAllData(mediaType, list -> {
            adapter = new DataAdapter(list, this);
            recycler.setAdapter(adapter);
        });

    }


    @Override
    public void onDataClickedCallback(Data data) {
        FragmentManager manager = getFragmentManager();
        if (manager != null) {
            InfoFragment fragment = InfoFragment.newInstance(data.getId());
            fragment.show(manager, fragment.getTag());
        }
    }

    @Override
    public void sort(SortType sortType) {
        DataSource.getAllData(mediaType, sortType, adapter::setList);
    }

    @Override
    public void filter(String query) {
        DataSource.getFiltered(mediaType, query, adapter::setList);
    }

    @Override
    public void searchCanceled() {
        DataSource.getAllData(mediaType, adapter::setList);
    }

}
