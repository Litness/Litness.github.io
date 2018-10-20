package com.example.litness.litness.Fragment;

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
import android.widget.Toast;

import com.example.litness.litness.Adapter.BarCardAdapter;
import com.example.litness.litness.BarObject;
import com.example.litness.litness.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class BarListFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    private List<BarObject> reportList = new ArrayList<>();
    private BarCardAdapter recyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;

    private String filter = "";
    private SwipeRefreshLayout swipeContainer;
    private boolean loading = true;

    public BarListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bar_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.view_fragment_user_report_history);
        recyclerViewAdapter = new BarCardAdapter(getContext(),reportList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        pullToRefresh();
        infiniteScroll();

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateFragment();
    }

    private void populateFragment() {
        if(filter.equals("")) {
            reportList.clear();
            reportList.add(new BarObject("Rounders", "$20", "< 10 min", "music", "10"));
            reportList.add(new BarObject("Alcove", "$0", "None", "none", "5"));
            reportList.add(new BarObject("Catch 22", "$0", "None", "music", "8"));
            reportList.add(new BarObject("Loosa", "$0", "None", "None", "5"));
        }
        else {
            reportList.clear();
            reportList.add(new BarObject("Catch 22", "$0", "None", "music", "8"));
            reportList.add(new BarObject("Loosa", "$0", "None", "None", "5"));
        }
    }

    private void addMore() {
        reportList.add(new BarObject("Rounders", "$20", "< 10 min", "music", "10"));
        reportList.add(new BarObject("Alcove", "$0", "None", "none", "5"));
        reportList.add(new BarObject("Catch 22", "$0", "None", "music", "8"));
        reportList.add(new BarObject("Loosa", "$0", "None", "None", "5"));

    }

    public void setFilter(String string) {
        filter = string;
        populateFragment();
    }

    private void pullToRefresh() {
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.fragment_user_report_history);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(true);
                populateFragment();
                if(swipeContainer.isRefreshing())
                    swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    private void infiniteScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {
                    if (loading) {
                        int visibleItemCount = mLayoutManager.getChildCount();
                        int totalItemCount = mLayoutManager.getItemCount();
                        int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                        if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                            loading = false;
                            addMore();
                            recyclerViewAdapter.notifyItemRangeRemoved(0,totalItemCount);
                            Toast.makeText(getActivity(),visibleItemCount + " " + totalItemCount + " " + pastVisibleItems,Toast.LENGTH_SHORT).show();
                            recyclerViewAdapter.notifyItemRangeInserted(0, mLayoutManager.getItemCount());
                            recyclerViewAdapter.notifyDataSetChanged();
                            loading = true;
                        }

                    }
                }
            }
        });
    }
}
