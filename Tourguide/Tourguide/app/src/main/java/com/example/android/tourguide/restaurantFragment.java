package com.example.android.tourguide;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class restaurantFragment extends Fragment {
    public restaurantFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_item_list, container, false);
        ArrayList<information> information = new ArrayList<information>();
        information.add(new information(R.string.Restaurant, R.string.em, R.drawable.restaurant));
        information.add(new information(R.string.restaurant_1, R.string.restaurant_1_descraption, R.drawable.restaurantaktaion));
        information.add(new information(R.string.restaurant_2, R.string.restaurant_2_descraption, R.drawable.restaurantnaoussa));
        information.add(new information(R.string.restaurant_3, R.string.restaurant_3_descraption, R.drawable.restaurantvolcanoblue));
        listAdapter ad = new listAdapter(getActivity(), information);
        ListView listView = View.findViewById(R.id.list);
        listView.setAdapter(ad);
        return View;}}
