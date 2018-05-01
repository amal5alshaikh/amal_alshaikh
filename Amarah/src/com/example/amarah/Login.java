package com.example.amarah;


import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Login extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final Button loginButton= (Button) findViewById(R.id.button1);
		final EditText passwd1 = (EditText) findViewById(R.id.EditText01);
		final EditText passwd2 = (EditText) findViewById(R.id.EditText02);
		@SuppressWarnings("unused")
		final EditText passwd3 = (EditText) findViewById(R.id.EditText03);
		@SuppressWarnings("unused")
		final EditText passwd4 = (EditText) findViewById(R.id.EditText04);
		final EditText uname = (EditText) findViewById(R.id.editText1);
		
		loginButton.setOnClickListener(new View.OnClickListener()
		{
		    public void onClick(View view) 
		    {
		    	if (checkPassword(uname.getText(), passwd1.getText(),passwd2.getText(),passwd3.getText(),passwd4.getText())) {
					Intent helloAndroidIntent = new Intent(Login.this,
							MainActivity.class);
					startActivity(helloAndroidIntent);
				}
		       Intent myIntent = new Intent(view.getContext(), Tabhost.class);
		 
		   
		        startActivityForResult(myIntent, 0);
		    }

			private boolean checkPassword(Editable text, Editable text2,
					Editable text3, Editable editable, Editable editable2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
