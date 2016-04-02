package com.main.java.babyapp.immunisations;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.main.java.babyapp.R;

public class NewImmunisationActivity extends AppCompatActivity {

	EditText edit_dategiven;
	ImageView img_dategiven;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_immunisation_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		edit_dategiven = (EditText)findViewById(R.id.edit_dategiven);
		edit_dategiven.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				setDate(view);
			}
		});
		
		img_dategiven = (ImageView)findViewById(R.id.img_dategiven);
		img_dategiven.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				setDate(view);
			}
		});
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
	
	private int year;
	private int month;
	private int day;
	public void setDate(View view){
		//showDialog(DATE_DIALOG_ID);
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
	    month = calendar.get(Calendar.MONTH);
	    day = calendar.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog dpd = new DatePickerDialog(this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
		    	year = selectedYear;
				month = selectedMonth;
				day = selectedDay;

				// set selected date into textview
				edit_dategiven.setText(new StringBuilder().append(day)
				   .append("/").append(month + 1).append("/").append(year)
				   .append(" "));
				Toast.makeText(getApplicationContext(), ""+edit_dategiven.getText().toString(), Toast.LENGTH_SHORT).show();
		    }
		 }, year, month, day);

		 dpd.show();
    }
	
	@Override
    public void onConfigurationChanged(Configuration newConfig)
    {
      super.onConfigurationChanged(newConfig);
    }
}