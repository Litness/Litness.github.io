package com.example.litness.litness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.litness.litness.Fragment.BarListFragment;

public class MainActivity extends AppCompatActivity {

    Fragment fragList;
    FragmentManager fragDaddy = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragList = new BarListFragment();
        fragDaddy.beginTransaction().add(R.id.main_fragframe, fragList, "List").show(fragList).commit();
    }
}
