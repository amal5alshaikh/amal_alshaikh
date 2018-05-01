package com.example.android.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by Amal Alshaikh on 3/10/2017.
 */

public class InventoryProvider extends ContentProvider {
    private static final UriMatcher Matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int Inventory = 1;
    private static final int Inventory_id = 2;

    static {
        Matcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, Inventory);
        Matcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", Inventory_id);
    }

    private SqlHelper inventoryHelper;

    @Override
    public boolean onCreate() {
        inventoryHelper = new SqlHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = inventoryHelper.getReadableDatabase();
        Cursor cursor;
        int matchCase = Matcher.match(uri);
        switch (matchCase) {
            case Inventory:
                cursor = db.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case Inventory_id:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(InventoryEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Wrong Database Query" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int matchCase = Matcher.match(uri);
        switch (matchCase) {
            case Inventory:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case Inventory_id:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("can not find URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int matchCase = Matcher.match(uri);
        switch (matchCase) {
            case Inventory:
                return insertProduct(uri, values);
            default:
                throw new IllegalArgumentException("Wrong URI" + uri);
        }
    }

    private Uri insertProduct(Uri uri, ContentValues values) {
        String name = values.getAsString(InventoryEntry.COLUMN_Inventory_NAME);
        if (name == null) {
            Toast.makeText(getContext(), "missing a product name", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("missing a product name");
        }

        Integer price = values.getAsInteger(InventoryEntry.COLUMN_Inventory_price);
        if (price == null && price < 0) {
            Toast.makeText(getContext(), "missing a product Price", Toast.LENGTH_SHORT).show();

            throw new IllegalArgumentException("Enter a price");
        }

        Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_Inventory_quantity);
        if (quantity != null && quantity < 0) {
            Toast.makeText(getContext(), "missing a product Quantity", Toast.LENGTH_SHORT).show();
            throw new IllegalArgumentException("Enter more Product");
        }
        SQLiteDatabase db = inventoryHelper.getWritableDatabase();


        long id = db.insert(InventoryEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e("1", "Error " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = inventoryHelper.getWritableDatabase();
        int DeleteInventory;
        final int matchCase = Matcher.match(uri);
        switch (matchCase) {
            case Inventory:
                DeleteInventory = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case Inventory_id:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                DeleteInventory = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Wrong In Deleting Table " + uri);
        }
        if (DeleteInventory != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return DeleteInventory;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        final int matchCase = Matcher.match(uri);
        switch (matchCase) {
            case Inventory:
                return productUpdate(uri, values, selection, selectionArgs);
            case Inventory_id:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return productUpdate(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int productUpdate(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        if (contentValues.containsKey(InventoryEntry.COLUMN_Inventory_NAME)) {
            String productName = contentValues.getAsString(InventoryEntry.COLUMN_Inventory_NAME);
            if (productName == null) {
                Toast.makeText(getContext(), "missing a product name", Toast.LENGTH_SHORT).show();
                throw new IllegalArgumentException("missing a product name");
            }
        }

        if (contentValues.containsKey(InventoryEntry.COLUMN_Inventory_price)) {
            Integer Price = contentValues.getAsInteger(InventoryEntry.COLUMN_Inventory_price);
            if (Price != null && Price < 0) {
                Toast.makeText(getContext(), "you have to Enter A price", Toast.LENGTH_SHORT).show();
                throw new IllegalArgumentException("you have to Enter A price ");
            }
        }
        if (contentValues.containsKey(InventoryEntry.COLUMN_Inventory_quantity)) {
            Integer quantity = contentValues.getAsInteger(InventoryEntry.COLUMN_Inventory_quantity);
            if (quantity != null && quantity < 0) {
                Toast.makeText(getContext(), "Quantity can't be Null", Toast.LENGTH_SHORT).show();
                throw new IllegalArgumentException("can't be Null ");
            }
        }
        if (contentValues.size() == 0) {
            return 0;
        }
        SQLiteDatabase database = inventoryHelper.getWritableDatabase();
        int updatedProduct = database.update(InventoryEntry.TABLE_NAME, contentValues, selection, selectionArgs);
        if (updatedProduct != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updatedProduct;
    }
}