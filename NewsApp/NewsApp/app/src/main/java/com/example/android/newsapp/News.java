package com.example.android.newsapp;
/**
 * Created by Amal Alshaikh on 16/9/2017.
 */
public class News {
    private String title;
    private String section;
    private String author;
    private String date;
    public String weburl;
    public News(String title, String section, String author, String date, String weburl) {
        this.title = title;
        this.section = section;
        this.author = author;
        this.date = date;
        this.weburl = weburl;}
    public String gettitle() {
        return title;
    }
    public String getsection() {
        return section;
    }
    public String getauthor() {return author;}
    public String getdate() {
        return date;
    }
    public  String getweburl() {return weburl;}}