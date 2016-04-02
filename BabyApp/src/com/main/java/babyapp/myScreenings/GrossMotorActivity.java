package com.main.java.babyapp.myScreenings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.PersonalSocialMenuAdapter;
import com.main.java.babyapp.globalObject.GlobalObject;

public class GrossMotorActivity extends AppCompatActivity {

	private ListView screening_listview;
	private PersonalSocialMenuAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_social_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText("Gross Motor");

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        screening_listview = (ListView)findViewById(R.id.personal_social_listview);
        try{
        	adapter = new PersonalSocialMenuAdapter(this, GlobalObject.personalSocial_item_List, GlobalObject.personalSocial_subList);
        	adapter.notifyDataSetChanged();
        	screening_listview.setAdapter(adapter);
        	screening_listview.invalidateViews();
        	screening_listview.refreshDrawableState();
        	
        	screening_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					System.out.println("CLICKED:"+position);
				}
			});
		}catch(NullPointerException npe){}
       
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