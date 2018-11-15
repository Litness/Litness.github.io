package com.example.litness.litness.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.litness.litness.Adapter.BarCardAdapter;
import com.example.litness.litness.Adapter.FilterAdapter;
import com.example.litness.litness.Bar;
import com.example.litness.litness.Client;
import com.example.litness.litness.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class BarListFragment extends Fragment {

    private RecyclerView rv;
    private RecyclerView rvFilter;
    private BarCardAdapter adapter;
    private FilterAdapter filterAdapter;

    private SwipeRefreshLayout swipeContainer;

    LinearLayout filterTainer;
    TextView tvNoBars;
    ImageView ivSearch;
    EditText etSearch;

    public BarListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_barcards, container, false);

        adapter = new BarCardAdapter(getContext());

        rv = v.findViewById(R.id.bars_rv);
        rv.setAdapter(adapter);

        rvFilter = v.findViewById(R.id.bars_rv_filter);
        filterAdapter = new FilterAdapter();
        rvFilter.setAdapter(filterAdapter);

        filterAdapter.filterList.clear();
        filterAdapter.filterList.addAll((Arrays.asList(getResources().getStringArray(R.array.filter_options))));
        filterAdapter.notifyDataSetChanged();

        adapter.updateBars(applyFilter(Client.barMap.values()));

        swipeContainer = v.findViewById(R.id.bars_sr);
        swipeContainer.setOnRefreshListener(this::actionSwipeRefresh);

        tvNoBars = v.findViewById(R.id.bars_alt_nobars);

        //Set the Image search button and edit text for it.
        ivSearch = v.findViewById(R.id.imageView2);
        etSearch = v.findViewById(R.id.bars_input_searchbox);

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private void actionSwipeRefresh() {
        swipeContainer.setRefreshing(true); {
            adapter.updateBars(applyFilter(Client.barMap.values()));
        }
        if (swipeContainer.isRefreshing())
            swipeContainer.setRefreshing(false);
    }

    private List<Bar> applyFilter(Collection<Bar> nonFilteredBarMap) {
        List<Bar> barList = new ArrayList<>(nonFilteredBarMap);
        return barList;
    }
}