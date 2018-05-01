package com.example.android.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract.InventoryEntry;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.android.inventoryapp.R.id.priceText;

public class InventoryEditor extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    static final int REQUEST_IMAGE_GET = 0;
    private static final int LOADER = 0;
    private EditText ProductNameText;
    private EditText PriceText;
    private EditText QuantityText;
    private ImageView ProductImage;
    private Uri UriProduct;

    //https://github.com/sprejjs/MyMemoriesDB/blob/master/app/src/main/java/com/spreys/mymemoriesdb/model/Memory.java
    //by Vlad Spreys
    //Published on Jul 29, 2017
    private static String bitToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    //https://github.com/sprejjs/MyMemoriesDB/blob/master/app/src/main/java/com/spreys/mymemoriesdb/model/Memory.java
    //by Vlad Spreys
    //Published on Jul 29, 2017
    private static Bitmap stringToBit(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_editor);
        final Button Increase = (Button) findViewById(R.id.increase);
        final Button decrease = (Button) findViewById(R.id.decrease);
        Intent intent = getIntent();
        UriProduct = intent.getData();
        if (UriProduct == null) {
            setTitle("Add New Product");
            Increase.setVisibility(View.GONE);
            decrease.setVisibility(View.GONE);
            invalidateOptionsMenu();
        } else {
            setTitle("Edit Product Info");
            getLoaderManager().initLoader(LOADER, null, this);
        }
        ProductNameText = (EditText) findViewById(R.id.NameText);
        PriceText = (EditText) findViewById(R.id.priceText);
        QuantityText = (EditText) findViewById(R.id.quantityText);
        ProductImage = (ImageView) findViewById(R.id.imageholder);

        ProductImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectImage();
            }
        });
        Increase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int Quantity = Integer.parseInt(QuantityText.getText().toString());
                if (Quantity == 100) {
                    ToastIncrease();
                } else {
                    String Increase = String.valueOf(Quantity + 1);
                    QuantityText.setText(Increase);
                }
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int Quantity = Integer.parseInt(QuantityText.getText().toString());
                if (Quantity <= 0) {
                    ToastDecrease();
                } else {
                    String decrease = String.valueOf(Quantity - 1);
                    QuantityText.setText(decrease);
                }
            }
        });
    }

    public void ToastIncrease() {
        Toast.makeText(this, "cant be more then 100", Toast.LENGTH_SHORT).show();
    }

    public void ToastDecrease() {
        Toast.makeText(this, "cant be less then 0", Toast.LENGTH_SHORT).show();
    }

    //https://developer.android.com/guide/components/intents-common.html
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }

    @Override
    //https://github.com/sprejjs/MyMemoriesDB/blob/master/app/src/main/java/com/spreys/mymemoriesdb/model/Memory.java
    //by Vlad Spreys
    //Published on Jul 29, 2017
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            try {
                Uri fullPhotoUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(fullPhotoUri);
                ProductImage.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void saveInventory() {
        ProductNameText = (EditText) findViewById(R.id.NameText);
        PriceText = (EditText) findViewById(priceText);
        QuantityText = (EditText) findViewById(R.id.quantityText);

        String productnameString = ProductNameText.getText().toString().trim();
        String pricestring = PriceText.getText().toString().trim();
        String quantityString = QuantityText.getText().toString().trim();
        Bitmap ProductImageString = ((BitmapDrawable) ProductImage.getDrawable()).getBitmap();

        if (UriProduct == null && TextUtils.isEmpty(productnameString) && TextUtils.isEmpty(pricestring) &&
                TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, ("one of the Item was empty could not save"), Toast.LENGTH_LONG).show();

            return;
        }
        if (UriProduct == null && TextUtils.isEmpty(productnameString) || TextUtils.isEmpty(pricestring) ||
                TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, ("one of the Item was empty could not save"), Toast.LENGTH_LONG).show();
            return;
        } else {
            ContentValues Itemvalues = new ContentValues();

            Itemvalues.put(InventoryEntry.COLUMN_Inventory_NAME, productnameString);
            Itemvalues.put(InventoryEntry.COLUMN_Inventory_price, pricestring);
            Itemvalues.put(InventoryEntry.COLUMN_Inventory_quantity, quantityString);
            Itemvalues.put(InventoryEntry.COLUMN_Inventory_IMAGE, bitToString(ProductImageString));


            Log.w("1", "data was inserted to data base");
            if (UriProduct == null) {
                Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, Itemvalues);
                if (newUri == null) {
                    Toast.makeText(this, ("No new product was add"), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, ("A new product was add"), Toast.LENGTH_SHORT).show();

                }
                finish();

            } else {
                int rowsAffected = getContentResolver().update(UriProduct, Itemvalues, null, null);
                if (rowsAffected == 0) {
                    Toast.makeText(this, ("The new product was not add "), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, ("product was Edited"), Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }

    public String getDetails() {
        String ProductString, PriceString, QuantityString;
        String details;
        ProductString = ProductNameText.getText().toString().trim();
        PriceString = PriceText.getText().toString().trim();
        QuantityString = QuantityText.getText().toString().trim();
        details = "\n" + ProductString + "\n" + PriceString + "\n" + QuantityString + "\n";
        return details;
    }

    //https://developer.android.com/guide/components/intents-common.html#Storage
    public void orderMore(View view) {
        Intent order = new Intent(Intent.ACTION_SENDTO);
        order.setData(Uri.parse("mailto:"));
        order.putExtra(Intent.EXTRA_TEXT, "need to order more of  " + getDetails() + "can you send please ASAP");
        order.putExtra(Intent.EXTRA_SUBJECT, "");
        if (order.resolveActivity(getPackageManager()) != null) {
            startActivity(order);
        }
    }

    private void delete() {
        if (UriProduct != null) {
            int rowsDeleted = getContentResolver().delete(UriProduct, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "product is not deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "product is deleted", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu editmenu) {
        getMenuInflater().inflate(R.menu.edit, editmenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveDialog();
                return true;
            case R.id.order_more:
                this.orderMore(null);
                return true;
            case R.id.action_delete:
                DeleteDialog();
                return true;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (UriProduct == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            MenuItem menuItem2 = menu.findItem(R.id.order_more);
            menuItem2.setVisible(false);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_Inventory_NAME,
                InventoryEntry.COLUMN_Inventory_price,
                InventoryEntry.COLUMN_Inventory_price,
                InventoryEntry.COLUMN_Inventory_IMAGE,
                InventoryEntry.COLUMN_Inventory_quantity};
        return new CursorLoader(this,
                UriProduct,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data == null || data.getCount() < 1) {
            return;
        }
        if (data.moveToFirst()) {
            int COLUMN_Inventory_name = data.getColumnIndex(InventoryEntry.COLUMN_Inventory_NAME);
            int COLUMN_Inventory_price = data.getColumnIndex(InventoryEntry.COLUMN_Inventory_price);
            int COLUMN_Inventory_Image = data.getColumnIndex(InventoryEntry.COLUMN_Inventory_IMAGE);
            int COLUMN_Inventory_quantity = data.getColumnIndex(InventoryEntry.COLUMN_Inventory_quantity);

            String productName = data.getString(COLUMN_Inventory_name);
            int price = data.getInt(COLUMN_Inventory_price);
            int quantity = data.getInt(COLUMN_Inventory_quantity);
            String productimage = data.getString(COLUMN_Inventory_Image);

            ProductNameText.setText(productName);
            QuantityText.setText(Integer.toString(quantity));
            PriceText.setText(Integer.toString(price));
            ProductImage.setImageBitmap(stringToBit(productimage));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ProductNameText.setText("");
        PriceText.setText("");
        QuantityText.setText("");
        ProductImage.setImageBitmap(null);
    }

    private void DeleteDialog() {
        AlertDialog.Builder confirmation = new AlertDialog.Builder(this);
        confirmation.setMessage("are sure you want to Delete this product?");
        confirmation.setIcon(R.drawable.error);
        confirmation.setTitle("Delete");
        confirmation.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        confirmation.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                delete();
                finish();
            }
        });
        AlertDialog confirmationDialog = confirmation.create();
        confirmationDialog.show();
    }

    private void saveDialog() {
        AlertDialog.Builder confirmation = new AlertDialog.Builder(this);
        confirmation.setMessage("are sure you want to Save this product?");
        confirmation.setIcon(R.drawable.error);
        confirmation.setTitle("Save");
        confirmation.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        confirmation.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                saveInventory();
            }
        });
        AlertDialog confirmationDialog = confirmation.create();
        confirmationDialog.show();
    }

}