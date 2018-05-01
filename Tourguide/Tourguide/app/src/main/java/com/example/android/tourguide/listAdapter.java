package com.example.android.tourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Amal Alshaikh on 18/8/2017.
 */
public class listAdapter extends ArrayAdapter<information> {
    public listAdapter(Context context, ArrayList<information> informations) {super(context, 0, informations);}
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View information_list = convertView;
        if (information_list == null) {
            information_list = LayoutInflater.from(getContext()).inflate(R.layout.toure_fragment_item_list, parent, false);}
        information info = getItem(position);
        TextView info_Name = information_list.findViewById(R.id.Name);
        TextView info_descraption = information_list.findViewById(R.id.descraption);
        ImageView info_image = information_list.findViewById(R.id.Info_image);
        info_Name.setText(info.getinformationName());
        info_descraption.setText(info.getinformationdescraption());
        info_image.setImageResource(info.getinformationimage());
        return information_list;}}