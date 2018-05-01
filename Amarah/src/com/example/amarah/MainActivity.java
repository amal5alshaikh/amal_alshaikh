package com.example.amarah;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;


public class MainActivity extends Activity {

	   private ProgressDialog pd = null;
	    private Object data = null;

	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        Intent goToNextActivity = new Intent(getApplicationContext(), Login.class);
	        startActivity(goToNextActivity);

	        // Show the ProgressDialog on this thread
	        this.pd = ProgressDialog.show(this, "Working..", "Downloading Data...", true, false);

	        // Start a new thread that will download all the data
	        new DownloadTask().execute("Any parameters my download task needs here");
	    }

	    private class DownloadTask extends AsyncTask<String, Void, Object> {
	         protected Object doInBackground(String... args) {
	             Log.i("MyApp", "Background thread starting");

	             // This is where you would do all the work of downloading your data

	             return "replace this with your data object";
	         }

	         protected void onPostExecute(Object result) {
	             // Pass the result data back to the main activity
	        	 MainActivity.this.data = result;

	             if (MainActivity.this.pd != null) {
	            	 MainActivity.this.pd.dismiss();
	             }
	         }
	    } 
	    

	}
