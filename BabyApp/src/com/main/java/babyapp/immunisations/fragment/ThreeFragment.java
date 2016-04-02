package com.main.java.babyapp.immunisations.fragment;

import com.main.java.babyapp.R;
import com.main.java.babyapp.adapters.ImmunisationMenuAdapter;
import com.main.java.babyapp.globalObject.GlobalObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;




public class ThreeFragment extends Fragment{

	private ListView immuni_listview;
	private ImmunisationMenuAdapter adapter;
	
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View rootView = inflater.inflate(R.layout.fragment_three, container,false);
    	immuni_listview = (ListView)rootView.findViewById(R.id.immuni_listview);
    	try{
        	adapter = new ImmunisationMenuAdapter(getActivity(), GlobalObject.doseList, GlobalObject.doseDateList);
        	adapter.notifyDataSetChanged();
        	immuni_listview.setAdapter(adapter);
        	immuni_listview.invalidateViews();
        	immuni_listview.refreshDrawableState();
        	
        	immuni_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
				}
			});
		}catch(NullPointerException npe){}
    	
        return rootView;
    }

}
