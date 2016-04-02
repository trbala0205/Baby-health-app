package com.main.java.babyapp.adapters;

import java.util.ArrayList;

import com.main.java.babyapp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class BabyBookletMenuAdapter  extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<String> menuList, hintList;
    private LayoutInflater inflater=null;
 
    public BabyBookletMenuAdapter(Activity _activity, ArrayList<String> _arrayList, ArrayList<String> _hintList) {
 
    	activity = _activity;
        menuList = _arrayList;
        hintList = _hintList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    public int getCount() {
        return menuList.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.baby_booklet_list_row_item, null);
 
        TextView txt_inverter_name = (TextView)view.findViewById(R.id.my_menu_name); // title
        TextView txt_cluster_module_count = (TextView)view.findViewById(R.id.my_menu_hint); // title
        ImageView my_thumbnail_img = (ImageView)view.findViewById(R.id.my_thumbnail_img);
 
        // Setting all values in listview
        txt_inverter_name.setText(menuList.get(position));
        txt_cluster_module_count.setText(hintList.get(position));
        
        if(position == 0)
        	txt_inverter_name.setTextColor(activity.getResources().getColor(R.color.red_500));
        if(position == 1)
        	txt_inverter_name.setTextColor(activity.getResources().getColor(R.color.yellow_500));
        if(position == 2)
        	txt_inverter_name.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        return view;
	}
}