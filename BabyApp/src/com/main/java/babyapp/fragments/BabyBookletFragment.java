package com.main.java.babyapp.fragments;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.main.java.babyapp.GrowthSummaryActivity;
import com.main.java.babyapp.HealthBookletActivity;
import com.main.java.babyapp.MyScreeningSummaryActivity;
import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.BabyBookletMenuAdapter;
import com.main.java.babyapp.addbio.AddBioMainActivity;
import com.main.java.babyapp.encyclopedia.MedicationActivity;
import com.main.java.babyapp.globalObject.GlobalObject;
import com.main.java.babyapp.immunisations.ImmunisationActivity;


public class BabyBookletFragment extends Fragment {

	private ListView booklet_listview;
	private BabyBookletMenuAdapter adapter;
	private Button btn_addbio;
	private LinearLayout health_booklet_layout, encyclopedia_layout, listview_layout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View rootView = inflater.inflate(R.layout.baby_booklet_fragment, container,false);
		
		//booklet_listview = (ListView)rootView.findViewById(R.id.my_booklet_listview);
		health_booklet_layout = (LinearLayout)rootView.findViewById(R.id.health_booklet_layout);
		encyclopedia_layout = (LinearLayout)rootView.findViewById(R.id.encyclopedia_layout);
		listview_layout = (LinearLayout)rootView.findViewById(R.id.listview_layout);
		
		try{
			booklet_listview = new ListView(getActivity());
			booklet_listview.setDivider(new ColorDrawable(getActivity().getResources().getColor(R.color.grey_400)));
			booklet_listview.setDividerHeight(1);
			booklet_listview.setFooterDividersEnabled(true);
        	adapter = new BabyBookletMenuAdapter(getActivity(), GlobalObject.menuList, GlobalObject.hintList);
        	adapter.notifyDataSetChanged();
        	booklet_listview.setAdapter(adapter);
        	booklet_listview.invalidateViews();
        	booklet_listview.refreshDrawableState();
        	setListViewHeightBasedOnChildren(booklet_listview);
        	booklet_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if(position == 0)
						startActivity(new Intent(getActivity(), ImmunisationActivity.class));
					else if(position == 1)
						startActivity(new Intent(getActivity(), MyScreeningSummaryActivity.class));
					else if(position == 2)
						startActivity(new Intent(getActivity(), GrowthSummaryActivity.class));
				}
			});
        	listview_layout.addView(booklet_listview);
		}catch(NullPointerException npe){}
		
		btn_addbio = (Button)rootView.findViewById(R.id.btn_addbio);
		btn_addbio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), AddBioMainActivity.class));
			}
		});
		
		health_booklet_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gotoHealthBookletpage(v);
			}
		});
		
		encyclopedia_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), MedicationActivity.class));
			}
		});
		
		return rootView;
	}
	
	public void gotoHealthBookletpage(View view){
		startActivity(new Intent(getActivity(), HealthBookletActivity.class));
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter list_adapter = listView.getAdapter();
	    if (list_adapter == null)
	        return;

	    int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
	    int totalHeight = 0;
	    View view = null;
	    for (int i = 0; i < list_adapter.getCount(); i++) {
	        view = list_adapter.getView(i, view, listView);
	        if (i == 0)
	            view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

	        view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
	        totalHeight += view.getMeasuredHeight();
	    }
	    //ViewGroup.LayoutParams params = listView.getLayoutParams();
	    //params.height = totalHeight + (listView.getDividerHeight() * (listview_adapter.getCount() - 1));
	    listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, totalHeight + (listView.getDividerHeight() * (list_adapter.getCount() - 1))));
	    listView.requestLayout();
	}
	
}
