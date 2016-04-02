package com.main.java.babyapp.myScreenings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.main.java.babyapp.R;

public class PhysicalExaminationActivity extends AppCompatActivity {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.physical_examination_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
	}
	
	MenuItem save;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_actions, menu);
		save = menu.findItem(R.id.action_save);
		save.setVisible(true);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
		//MenuItem action_setting = menu.findItem(R.id.action_settings);
		//action_setting.setVisible(true);
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		switch (item.getItemId()) {
        case android.R.id.home:
            return true;
        case R.id.action_settings:
    		return true;
    }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}