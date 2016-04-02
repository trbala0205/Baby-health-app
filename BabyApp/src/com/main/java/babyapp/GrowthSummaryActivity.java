package com.main.java.babyapp;

import com.main.java.babyapp.adapters.MyGrowthSummaryMenuAdapter;
import com.main.java.babyapp.fragments.VisualAcuityActivity;
import com.main.java.babyapp.globalObject.GlobalObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class GrowthSummaryActivity extends AppCompatActivity {
	
	private ListView screening_listview;
	private MyGrowthSummaryMenuAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.growth_summary_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        screening_listview = (ListView)findViewById(R.id.growth_summary_listview);
        try{
        	adapter = new MyGrowthSummaryMenuAdapter(this, GlobalObject.growthList, GlobalObject.screeningHintList);
        	adapter.notifyDataSetChanged();
        	screening_listview.setAdapter(adapter);
        	screening_listview.invalidateViews();
        	screening_listview.refreshDrawableState();
        	
        	screening_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					startActivity(new Intent(GrowthSummaryActivity.this, VisualAcuityActivity.class));
				}
			});
		}catch(NullPointerException npe){}
        
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}