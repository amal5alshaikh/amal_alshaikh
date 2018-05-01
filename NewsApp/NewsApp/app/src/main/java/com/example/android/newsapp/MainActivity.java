package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    public static final String Error_Log = MainActivity.class.getSimpleName();
    private static final String NEWS_URl = "https://content.guardianapis.com/search?api-key=c1d9cc90-8b88-4813-8bd5-cc477bd0137f";
    String urlc;
    private TextView emptyStatus;
    private NewsAdapter newsAdapter;
    public  String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            int Respon = urlConnection.getResponseCode();
            if (Respon == 200) {
                Log.w(String.valueOf(1), "OK");
            } else Log.w(String.valueOf(1), "wrong");
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();}
            if (inputStream != null) {
                inputStream.close();}}
        return jsonResponse;}
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();}}
        return output.toString();}
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<News>>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
            }
            @Override
            public List<News> loadInBackground() {
                URL url = NewLoader.createUrl(NEWS_URl);
                String jsonResponse = "";
                try {
                    jsonResponse = makeHttpRequest(url);
                } catch (IOException e) {}
                List<News> News_update = NewLoader.extractitemFromJson(jsonResponse);
                return News_update;}};}
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsinfo) {

        View progress = findViewById(R.id.progressBar);
        newsAdapter.clear();
        if (newsinfo != null && !newsinfo.isEmpty()) {
            newsAdapter.addAll(newsinfo);
            progress.setVisibility(View.INVISIBLE);
            getNumber();
        } else {
            progress.setVisibility(View.INVISIBLE);
            emptyStatus.setText(R.string.no_News);
        }
        final ListView NewsList = (ListView) findViewById(R.id.News_list);
        NewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                News newsa = newsAdapter.getItem(i);
                urlc = newsa.getweburl();
                openWebPage();
            }
        });}

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkConaction();
        emptyStatus = (TextView) findViewById(R.id.empty);
        ListView newsListView = (ListView) findViewById(R.id.News_list);
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(newsAdapter);
        newsListView.setEmptyView(emptyStatus);
        emptyStatus.setText(R.string.no_News);
    }
    public void networkConaction() {
        View progress = findViewById(R.id.progressBar);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final TextView ErrorTextView = (TextView) findViewById(R.id.error);
        final ImageView interimg = (ImageView) findViewById(R.id.intimage);
        if (activeNetwork != null && activeNetwork.isConnected()) {
            progress.setVisibility(View.VISIBLE);
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();
            interimg.setImageResource(R.drawable.guardian);
            ErrorTextView.setText("Connected");
        } else {
            progress.setVisibility(View.GONE);
            getSupportLoaderManager().initLoader(1, null, this).cancelLoad();
            interimg.setImageResource(R.drawable.guardian);
            ErrorTextView.setText(" not Connected");}}
    public void openWebPage() {
        Uri webpage = Uri.parse(urlc);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}}
    public void getNumber() {
        int num;
        String newsNumber;
        ListView newsListView = (ListView) findViewById(R.id.News_list);
        TextView textnumber = (TextView) findViewById(R.id.number);
        num = newsListView.getCount();
        newsNumber = String.valueOf(num);
        textnumber.setText(newsNumber);}}