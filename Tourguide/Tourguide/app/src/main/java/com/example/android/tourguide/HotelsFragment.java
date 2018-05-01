package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelsFragment extends Fragment {
    public HotelsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_item_list, container, false);
        ArrayList<information> information = new ArrayList<information>();
        information.add(new information(R.string.Hotels, R.string.em, R.drawable.hotel));
        information.add(new information(R.string.hotel_1, R.string.hotel_1_descraption, R.drawable.hotelsuperb));
        information.add(new information(R.string.hotel_2, R.string.hotel_2_descraption, R.drawable.hotelnautilusdome));
        information.add(new information(R.string.hotel_3, R.string.hotel_3_descraption, R.drawable.hotelhellas));
        listAdapter ad = new listAdapter(getActivity(), information);
        ListView listView = View.findViewById(R.id.list);
        listView.setAdapter(ad);
        return View;}}