package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by Amal Alshaikh on 27/9/2017.
 */
public class SqlHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Inventory.db";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_Inventory_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_Inventory_quantity + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_Inventory_IMAGE + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_Inventory_price + " INTEGER NOT NULL); ";
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int newVersion) {
    }
}