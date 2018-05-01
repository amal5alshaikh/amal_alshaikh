package com.example.android.tourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ToureViewAdapter extends FragmentPagerAdapter {
    public ToureViewAdapter(FragmentManager fm) {
        super(fm);
    }
    public Fragment getItem(int pageNumber) {
        if (pageNumber == 0) {
            return new restaurantFragment();
        } else if (pageNumber == 1) {
            return new muzeumFragment();
        } else if (pageNumber == 2) {
            return new HotelsFragment();
        } else {
            return new shopsFragment();}}
    @Override
    public int getCount() {
        return 4;}}