package com.example.android.newsapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by Amal Alshaikh on 16/9/2017.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> News) {
        super(context, 0, News);}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list = convertView;
        News News = getItem(position);
        if (list == null) {
            list = LayoutInflater.from(getContext()).inflate(R.layout.list_news, parent, false);}
        TextView title = (TextView) list.findViewById(R.id.Title);
        TextView section = (TextView) list.findViewById(R.id.section);
        TextView author = (TextView) list.findViewById(R.id.author);
        TextView date = (TextView) list.findViewById(R.id.date);
        title.setText(News.gettitle());
        section.setText(News.getsection());
        author.setText(News.getauthor());
        date.setText(News.getdate());
        return list;}}