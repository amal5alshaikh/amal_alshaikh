package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class muzeumFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_item_list, container, false);
        ArrayList<information> information = new ArrayList<information>();
        information.add(new information(R.string.Museum, R.string.em, R.drawable.museum));
        information.add(new information(R.string.Museum_1, R.string.Museum_1_descraption, R.drawable.museumarchaeological));
        information.add(new information(R.string.Museum_2, R.string.Museum_2_descraption, R.drawable.museumargyros));
        information.add(new information(R.string.Museum_3, R.string.Museum_3_descraption, R.drawable.museumthepast));
        listAdapter ad = new listAdapter(getActivity(), information);
        ListView listView = View.findViewById(R.id.list);
        listView.setAdapter(ad);
        return View;}}
