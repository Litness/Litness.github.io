package com.example.litness.litness;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.litness.litness.Adapter.BarCardAdapter;
import com.example.litness.litness.Adapter.FilterAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvCards;
    private BarCardAdapter adapter;

    private SwipeRefreshLayout swipeContainer;

    LinearLayout filterTainer;
    TextView tvNoBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        rvCards = findViewById(R.id.main_card_rv);
        adapter = new BarCardAdapter(this);
        rvCards.setAdapter(adapter);

        LinearLayout ll = findViewById(R.id.main_container_filters);
        for(String s : getResources().getStringArray(R.array.filter_options)){
            View v = getLayoutInflater().inflate(R.layout.adapter_filter_off, null, false);
            View w = getLayoutInflater().inflate(R.layout.adapter_filter_on, null, false);
            ((TextView) v.findViewById(R.id.adapter_alt_filter)).setText(s);
            ((TextView) w.findViewById(R.id.adapter_alt_filter)).setText(s);
            w.findViewById(R.id.adapter_container).setVisibility(View.GONE);
            v.findViewById(R.id.adapter_container).setOnClickListener(x->{
                v.findViewById(R.id.adapter_container).setVisibility(View.GONE);
                w.findViewById(R.id.adapter_container).setVisibility(View.VISIBLE);
            });
            w.findViewById(R.id.adapter_container).setOnClickListener(x->{
                w.findViewById(R.id.adapter_container).setVisibility(View.GONE);
                v.findViewById(R.id.adapter_container).setVisibility(View.VISIBLE);
            });
            ll.addView(v);
            ll.addView(w);
        }

        swipeContainer = findViewById(R.id.main_sr);
        swipeContainer.setOnRefreshListener(this::actionSwipeRefresh);

        tvNoBars = findViewById(R.id.main_alt_nobars);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.input_filter);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        //fixes all the colors for searching with the toolbar
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.PrimaryLight));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.PrimaryLight));

        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.ic_search_24dp);
        ImageView closeIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeIcon.setImageResource(R.drawable.ic_close_primary_24dp);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //updateFilters(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //updateFilters(newText);
                return false;
            }
        });
        searchView.setOnCloseListener( () -> {
            //updateFilters("");
            return false;
        });
        return true;
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
/*    Fragment fragList;
    FragmentManager fragDaddy = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        fragList = new BarListFragment();
        fragDaddy.beginTransaction().add(R.id.main_fragframe, fragList, "List").show(fragList).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.input_filter);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        //fixes all the colors for searching with the toolbar
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.PrimaryLight));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.PrimaryLight));

        ImageView searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.ic_search_24dp);
        ImageView closeIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeIcon.setImageResource(R.drawable.ic_close_primary_24dp);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //updateFilters(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //updateFilters(newText);
                return false;
            }
        });
        searchView.setOnCloseListener( () -> {
            //updateFilters("");
            return false;
        });
        return true;
    }*/
}
