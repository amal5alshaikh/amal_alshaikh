package com.example.amarah;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Apartments extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apartments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apartments, menu);
		return true;
	}

}
