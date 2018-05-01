package com.example.android.inventoryapp.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

import static com.example.android.inventoryapp.R.id.price;

/**
 * Created by Amal Alshaikh on 5/10/2017.
 */
public class InventoryAdapter extends CursorAdapter {
    public InventoryAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    //https://github.com/sprejjs/MyMemoriesDB/blob/master/app/src/main/java/com/spreys/mymemoriesdb/model/Memory.java
    //by Vlad Spreys
    //Published on Jul 29, 2017
    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {
        Button Sale = (Button) view.findViewById(R.id.sell);
        TextView productNameText = (TextView) view.findViewById(R.id.name);
        TextView priceText = (TextView) view.findViewById(price);
        TextView quantityText = (TextView) view.findViewById(R.id.quantity);
        ImageView productImage = (ImageView) view.findViewById(R.id.Productimage);

        int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Inventory_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Inventory_price);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Inventory_quantity);
        int imageIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_Inventory_IMAGE);

        final long currentProduct = cursor.getLong(cursor.getColumnIndex(InventoryEntry._ID));
        String productName = cursor.getString(productNameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        String quantity = cursor.getString(quantityColumnIndex);
        String Image = cursor.getString(imageIndex);
        final String Quantity = cursor.getString(quantityColumnIndex);

        productNameText.setText(productName);
        priceText.setText(price);
        quantityText.setText(quantity);
        productImage.setImageBitmap(stringToBitmap(Image));


        Sale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ContentValues ProductValues = new ContentValues();
//https://developer.android.com/guide/topics/providers/content-provider-basics.html?utm_source=udacity&utm_medium=course&utm_campaign=android_basics#Updating
                Uri productUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI, currentProduct);
                int NewQuantity = Integer.parseInt(Quantity);
                if (NewQuantity != 0) {
                    ProductValues.put(InventoryEntry.COLUMN_Inventory_quantity, NewQuantity - 1);
                    context.getContentResolver().update(productUri, ProductValues, null, null);
                } else {
                    Toast.makeText(v.getContext(), "Item out of stock", Toast.LENGTH_LONG).show();
                }
            }
        });
        if (Quantity.equals("0")) {
            Sale.setVisibility(View.INVISIBLE);
        } else Sale.setVisibility(View.VISIBLE);
    }
}