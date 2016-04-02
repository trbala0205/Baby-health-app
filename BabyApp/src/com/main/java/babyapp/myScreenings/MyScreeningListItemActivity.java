package com.main.java.babyapp.myScreenings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.MyScreeningListItemAdapter;
import com.main.java.babyapp.globalObject.GlobalObject;

public class MyScreeningListItemActivity extends AppCompatActivity {

	private ListView screening_listview;
	private MyScreeningListItemAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_screening_list_item_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        screening_listview = (ListView)findViewById(R.id.screening_summary_listview);
        try{
        	adapter = new MyScreeningListItemAdapter(this, GlobalObject.my_scr_item_List);
        	adapter.notifyDataSetChanged();
        	screening_listview.setAdapter(adapter);
        	screening_listview.invalidateViews();
        	screening_listview.refreshDrawableState();
        	
        	screening_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				}
			});
		}catch(NullPointerException npe){}
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}