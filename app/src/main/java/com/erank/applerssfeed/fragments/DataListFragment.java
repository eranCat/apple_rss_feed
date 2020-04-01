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
import com.erank.applerssfeed.utils.DataAdapter;
import com.erank.applerssfeed.utils.Filterable;
import com.erank.applerssfeed.utils.Media;
import com.erank.applerssfeed.utils.SortType;
import com.erank.applerssfeed.utils.Sortable;
import com.erank.applerssfeed.utils.room.DataSource;

import java.util.List;


public class DataListFragment extends Fragment
        implements DataAdapter.OnDataCallback , Sortable, Filterable {

    private Media media;
    private DataAdapter adapter;

    public static DataListFragment newInstance(Media type) {

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

        media = (Media) getArguments().getSerializable("type");
        List<Data> allData = DataSource.getAllData(media);

        adapter = new DataAdapter(allData, this);
        recycler.setAdapter(adapter);
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
        List<Data> list = DataSource.getAllData(media, sortType);
        adapter.setList(list);
    }

    @Override
    public void filter(String query) {
        List<Data> list = DataSource.getFiltered(this.media, query);
        adapter.setList(list);
    }

    @Override
    public void searchCanceled() {
        List<Data> list = DataSource.getAllData(media);
        adapter.setList(list);
    }
}
