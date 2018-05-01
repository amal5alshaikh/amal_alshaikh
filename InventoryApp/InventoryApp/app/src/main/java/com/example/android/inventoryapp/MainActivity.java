package com.example.android.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.inventoryapp.data.InventoryAdapter;
import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER = 1;
    InventoryAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        Adapter = new InventoryAdapter(this, null);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InventoryEditor.class);
                startActivity(intent);
            }
        });
        ListView InventoryList = (ListView) findViewById(R.id.productlist);
        View empList = findViewById(R.id.empList);
        InventoryList.setEmptyView(empList);
        Adapter = new InventoryAdapter(this, null);
        InventoryList.setAdapter(Adapter);
        InventoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InventoryEditor.class);
                Uri URI = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, l);
                intent.setData(URI);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(LOADER, null, this);
    }

    public void insertproduct() {
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_Inventory_NAME, "Car");
        values.put(InventoryEntry.COLUMN_Inventory_price, "55000");
        values.put(InventoryEntry.COLUMN_Inventory_IMAGE, getString(R.string.image));
        values.put(InventoryEntry.COLUMN_Inventory_quantity, "5");
        Log.w("1", "data was inserted to database");
        getContentResolver().insert(InventoryEntry.CONTENT_URI, values);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {InventoryEntry._ID,
                InventoryEntry.COLUMN_Inventory_NAME,
                InventoryEntry.COLUMN_Inventory_price,
                InventoryEntry.COLUMN_Inventory_IMAGE,
                InventoryEntry.COLUMN_Inventory_quantity};
        return new CursorLoader(this, InventoryEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Adapter.swapCursor(null);
    }

    private void deleteAll() {
        int Deleted = getContentResolver().delete(InventoryEntry.CONTENT_URI, null, null);
        Log.w("1", Deleted + " rows deleted from database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_product:
                insertproduct();
                return true;
            case R.id.Delete:
                DeleteallDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DeleteallDialog() {
        AlertDialog.Builder confirmation = new AlertDialog.Builder(this);
        confirmation.setIcon(R.drawable.error);
        confirmation.setTitle("Delete");
        confirmation.setMessage("are sure you want to Delete ALL the product?");
        confirmation.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        confirmation.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteAll();
            }
        });
        AlertDialog alertDialog = confirmation.create();
        alertDialog.show();
    }
}