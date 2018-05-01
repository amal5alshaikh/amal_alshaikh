package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import static com.example.android.tourguide.R.id.main_pager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager) findViewById(main_pager);
        ToureViewAdapter ad = new ToureViewAdapter(getSupportFragmentManager());
        vp.setAdapter(ad);
    }
}