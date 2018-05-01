package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.newsapp.MainActivity.Error_Log;

/**
 * Created by Amal Alshaikh on 21/9/2017.
 */

public class NewLoader {
    public static List<News> extractitemFromJson(String newsjson) {
        List<News> NewsArray = new ArrayList<>();
        if (TextUtils.isEmpty(newsjson)) {
            return null;}
        try {
            JSONObject baseJsonResponse = new JSONObject(newsjson);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");
            if (resultsArray.length() > 0) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject newsObject = resultsArray.getJSONObject(i);
                    String title = newsObject.getString("webTitle");
                    String weburl = newsObject.getString("webUrl");
                    String sectionName = newsObject.getString("sectionName");
                    String datas = "";
                    if (newsObject.has("webPublicationDate")) {
                        datas = newsObject.getString("webPublicationDate");}
                    else {datas = "no date";}
                    String author = "";
                    if (newsObject.has("author")) {
                        author = newsObject.getString("author");}
                    else {author = "No Author";}
                    News newsobject = new News(title, sectionName, author, datas, weburl);
                    NewsArray.add(newsobject);}}}
        catch (JSONException e) {
            Log.w(Error_Log, "JSON Error", e);
        }
        return NewsArray;
    }
    public static URL createUrl(String urlLink) {
        URL url = null;
        try {
            url = new URL(urlLink);
        } catch (MalformedURLException exception) {
            Log.w(Error_Log, "Error", exception);
            return null;}
        return url;}}