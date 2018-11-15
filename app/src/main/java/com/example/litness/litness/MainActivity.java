package com.example.litness.litness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.litness.litness.Fragment.BarListFragment;

public class MainActivity extends AppCompatActivity {

    Fragment fragList;
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
    }
}
