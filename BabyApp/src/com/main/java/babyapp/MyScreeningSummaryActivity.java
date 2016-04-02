package com.main.java.babyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.main.java.babyapp.adapters.ScreeningSummaryMenuAdapter;
import com.main.java.babyapp.globalObject.GlobalObject;
import com.main.java.babyapp.myScreenings.MyScreeningListItemActivity;

public class MyScreeningSummaryActivity extends AppCompatActivity {

	private ListView screening_listview;
	private ScreeningSummaryMenuAdapter adapter;
	private Button btn_open_health_booklet;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screening_summary_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        screening_listview = (ListView)findViewById(R.id.screening_summary_listview);
        try{
        	adapter = new ScreeningSummaryMenuAdapter(this, GlobalObject.screeningList, GlobalObject.screeningHintList);
        	adapter.notifyDataSetChanged();
        	screening_listview.setAdapter(adapter);
        	screening_listview.invalidateViews();
        	screening_listview.refreshDrawableState();
        	
        	screening_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					System.out.println("CLICKED:"+position);
					startActivity(new Intent(MyScreeningSummaryActivity.this, MyScreeningListItemActivity.class));
				}
			});
		}catch(NullPointerException npe){}
        
        btn_open_health_booklet = (Button)findViewById(R.id.btn_open_health_booklet);
        btn_open_health_booklet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyScreeningSummaryActivity.this, HealthBookletActivity.class));
			}
		});
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}