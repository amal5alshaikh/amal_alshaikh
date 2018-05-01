package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.Date;
import data.HabitSqlHelper;
import static android.database.DatabaseUtils.dumpCursorToString;
import static data.habitContract.hapit;


public class MainActivity extends AppCompatActivity {
    HabitSqlHelper mDbHelper = new HabitSqlHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        SQLiteDatabase HabitSqlHelperRead = mDbHelper.getReadableDatabase();
        super.onCreate(savedInstanceState);
        insertHapit();
        readHapit();}

    private void insertHapit() {
        SQLiteDatabase HabitSqlHelperwrite = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(hapit.Colum_HABIT_NAME, "Drinking water");
        values.put(hapit.Colum_DAY, new Date().toString());
        values.put(hapit.Colum_done, "notDone");
        values.put(hapit.Colum_GOAL, 10);
        values.put(hapit.Colum_ACHIEVABLE, 2);

        ContentValues valuestwo = new ContentValues();
        valuestwo.put(hapit.Colum_HABIT_NAME, "Taking Metformin");
        valuestwo.put(hapit.Colum_DAY, new Date().toString());
        valuestwo.put(hapit.Colum_done, "Done");
        valuestwo.put(hapit.Colum_GOAL, 10);
        valuestwo.put(hapit.Colum_ACHIEVABLE, 10);

        long newRowId = HabitSqlHelperwrite.insert(hapit.TABLE_NAME, null, values);
        long newRowId2 = HabitSqlHelperwrite.insert(hapit.TABLE_NAME, null, valuestwo);}

    public Cursor readHapit() {
        SQLiteDatabase HabitSqlHelperRead = mDbHelper.getReadableDatabase();
        String[] projection = {
                hapit.Colum_HABIT_NAME,
                hapit.Colum_DAY};
        String selection = hapit.Colum_done + " = ?";
        String[] selectionArgs = {"Done"};
        String sortOrder = hapit.Colum_HABIT_NAME + " ASC";
        Cursor cursor = HabitSqlHelperRead.query(hapit.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        //https://developer.android.com/reference/android/database/DatabaseUtils.html
        Log.i("the reusult", dumpCursorToString(cursor));
        cursor.close();
        return cursor;}}





