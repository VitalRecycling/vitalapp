package com.example.android.vitalrecycling;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.vitalrecycling.R;
import com.example.android.vitalrecycling.SectionsPageAdapter;
import com.example.android.vitalrecycling.Tab1FragmentR;
import com.example.android.vitalrecycling.Tab2FragmentR;
import com.example.android.vitalrecycling.Tab3FragmentR;

public class RecycleActivity extends AppCompatActivity {

    //used to debug the application using logcat
    private static final String TAG = "RecycleActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // set up the ViewPager with the Section adapter
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = findViewById(R.id.fabHome2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(RecycleActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    //tabs setup...can add more
    private void setupViewPager(ViewPager viewPager){

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1FragmentR(), "Info");
        adapter.addFragment(new Tab2FragmentR(), "Contacts");
        adapter.addFragment(new Tab3FragmentR(), "Location");
        viewPager.setAdapter(adapter);

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}
