package com.main.java.babyapp.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.main.java.babyapp.R;

public class MyScreeningListItemAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<String> menuList;
    private LayoutInflater inflater=null;
 
    public MyScreeningListItemAdapter(Activity _activity, ArrayList<String> _arrayList) {
 
    	activity = _activity;
        menuList = _arrayList;
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
            view = inflater.inflate(R.layout.my_screening_list_item_row, null);
 
        TextView txt_inverter_name = (TextView)view.findViewById(R.id.my_menu_name); // title
 
        // Setting all values in listview
        txt_inverter_name.setText(menuList.get(position));

        return view;
	}
}