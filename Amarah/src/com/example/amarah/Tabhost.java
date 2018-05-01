package com.example.amarah;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class Tabhost extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        
        TabHost th = (TabHost)findViewById(R.id.tabhost);
        th.setup();
        TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("مستأجرين");
        th.addTab(specs);
        
         specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("عمارات");
        th.addTab(specs);
        
         specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("تقارير");
        th.addTab(specs);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tabhost, menu);
        return true;
      
        
    }
    
}

