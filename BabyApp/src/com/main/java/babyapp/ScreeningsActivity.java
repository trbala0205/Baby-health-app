package com.main.java.babyapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.main.java.babyapp.adapters.ExpandableListAdapter;
import com.main.java.babyapp.adapters.MyGrowthSummaryMenuAdapter;
import com.main.java.babyapp.myScreenings.FineMotorAdaptiveActivity;
import com.main.java.babyapp.myScreenings.GrossMotorActivity;
import com.main.java.babyapp.myScreenings.GrowthActivity;
import com.main.java.babyapp.myScreenings.LanguageActivity;
import com.main.java.babyapp.myScreenings.OtherScreeningActivity;
import com.main.java.babyapp.myScreenings.OutcomeActivity;
import com.main.java.babyapp.myScreenings.PersonalSocialActivity;
import com.main.java.babyapp.myScreenings.PhysicalExaminationActivity;

public class ScreeningsActivity extends AppCompatActivity {
	
	private ListView screening_listview;
	private MyGrowthSummaryMenuAdapter adapter;
	private ExpandableListView expandableListView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screening_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        /*screening_listview = (ListView)findViewById(R.id.growth_summary_listview);
        try{
        	adapter = new MyGrowthSummaryMenuAdapter(this, GlobalObject.growthList, GlobalObject.screeningHintList);
        	adapter.notifyDataSetChanged();
        	screening_listview.setAdapter(adapter);
        	screening_listview.invalidateViews();
        	screening_listview.refreshDrawableState();
        	
        	screening_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				}
			});
		}catch(NullPointerException npe){}*/
        
        expandableListView = (ExpandableListView) findViewById(R.id.visual_check_expandable_listview);
		// Setting group indicator null for custom indicator
		expandableListView.setGroupIndicator(null);
		
		setItems();
	}
	
	// Setting headers and childs to expandable listview
		void setItems(){
			
			// Array list for header
			ArrayList<String> header = new ArrayList<String>();
			header.add("DEVELOPMENTAL CHECKLIST");
			header.add("GROWTH");
			header.add("OTHER SCREENING");
			header.add("PHYSICAL EXAMINATION");
			header.add("OUTCOME");
			// Array list for child items
			List<String> child1 = new ArrayList<String>();
			child1.add("Personal Social");
			child1.add("Fine Motor-Adaptive");
			child1.add("Language");
			child1.add("Gross Motor");
			List<String> child2 = new ArrayList<String>();
			List<String> child3 = new ArrayList<String>();
			List<String> child4 = new ArrayList<String>();
			List<String> child5 = new ArrayList<String>();
			
			// Hash map for both header and child
			HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
			
			// Adding header and childs to hash map
			hashMap.put(header.get(0), child1);
			hashMap.put(header.get(1), child2);
			hashMap.put(header.get(2), child3);
			hashMap.put(header.get(3), child4);
			hashMap.put(header.get(4), child5);
			
			ExpandableListAdapter adapter = new ExpandableListAdapter(ScreeningsActivity.this, header, hashMap);
			// Setting adpater over expandablelistview
			expandableListView.setAdapter(adapter);
			//setListViewHeightBasedOnChildren(expandableListView);
			expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
				
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					Toast.makeText(getApplicationContext(), "OUTER ITEM CLICKED", Toast.LENGTH_SHORT).show();
					if(groupPosition == 1)
						startActivity(new Intent(ScreeningsActivity.this, GrowthActivity.class));
					else if(groupPosition == 2)
						startActivity(new Intent(ScreeningsActivity.this, OtherScreeningActivity.class));
					else if(groupPosition == 3)
						startActivity(new Intent(ScreeningsActivity.this, PhysicalExaminationActivity.class));
					else if(groupPosition == 4)
						startActivity(new Intent(ScreeningsActivity.this, OutcomeActivity.class));
						
					return true;
				}
			});	
				
			expandableListView.setOnChildClickListener(new OnChildClickListener() {
				
				@Override
				public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
					Toast.makeText(getApplicationContext(), "INNER ITEM CLICKED", Toast.LENGTH_SHORT).show();
					if(groupPosition == 0){
						if(childPosition == 0)
							startActivity(new Intent(ScreeningsActivity.this, PersonalSocialActivity.class));
						else if(childPosition == 1)
							startActivity(new Intent(ScreeningsActivity.this, FineMotorAdaptiveActivity.class));
						else if(childPosition == 1)
							startActivity(new Intent(ScreeningsActivity.this, LanguageActivity.class));
						else 
							startActivity(new Intent(ScreeningsActivity.this, GrossMotorActivity.class));
					}
					return true;
				}
			});
		}
		
		/*public static void setListViewHeightBasedOnChildren(ExpandableListView listView) {
			ListAdapter list_adapter = listView.getAdapter();
		    if (list_adapter == null)
		        return;

		    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
		    int totalHeight = 0;
		    View view = null;
		    for (int i = 0; i < list_adapter.getCount(); i++) {
		        view = list_adapter.getView(i, view, listView);
		        if (i == 0)
		            view.setLayoutParams(new LinearLayout.LayoutParams(desiredWidth, LinearLayout.LayoutParams.WRAP_CONTENT));

		        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
		        totalHeight += view.getMeasuredHeight();
		    }
		    //ViewGroup.LayoutParams params = listView.getLayoutParams();
		    //params.height = totalHeight + (listView.getDividerHeight() * (listview_adapter.getCount() - 1));
		    listView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, totalHeight + (listView.getDividerHeight() * (list_adapter.getCount() - 1))));
		    listView.requestLayout();
		}*/
		
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}