package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class shopsFragment extends Fragment {
    public shopsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View View = inflater.inflate(R.layout.fragment_item_list, container, false);
        ArrayList<information> information = new ArrayList<information>();
        information.add(new information(R.string.shop, R.string.em, R.drawable.shop));
        information.add(new information(R.string.shop_1, R.string.shop_1_descraption, R.drawable.shopbaseggio));
        information.add(new information(R.string.shop_2, R.string.shop_2_descraption, R.drawable.shopepymo));
        information.add(new information(R.string.shop_3, R.string.shop_3_descraption, R.drawable.shopmarmarini));
        listAdapter ad = new listAdapter(getActivity(), information);
        ListView listView = (ListView) View.findViewById(R.id.list);
        listView.setAdapter(ad);
        return View;}}