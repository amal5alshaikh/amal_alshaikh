package com.example.android.tourguide;

/**
 * Created by Amal Alshaikh on 16/8/2017.
 */
public class information {
    private int minformationName;
    private int minformationdescraption;
    private int minformationImage;
    public information(int informationName, int informationscraption, int informationimage) {
        minformationName = informationName;
        minformationdescraption = informationscraption;
        minformationImage = informationimage;}
    public int getinformationName() {
        return minformationName;}
    public int getinformationdescraption() {
        return minformationdescraption;}
    public int getinformationimage() {
        return minformationImage;}}