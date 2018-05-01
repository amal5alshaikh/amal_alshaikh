package com.example.android.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Amal Alshaikh on 27/9/2017.
 */
public final class InventoryContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.inventory";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY = "Inventory";

    private InventoryContract() {
    }

    public static final class InventoryEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;
        public final static String TABLE_NAME = "Inventory";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_Inventory_NAME = "name";
        public final static String COLUMN_Inventory_quantity = "quantity";
        public final static String COLUMN_Inventory_IMAGE = "image";
        public final static String COLUMN_Inventory_price = "price";
    }
}