package com.main.java.babyapp.healthBooklet;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.main.java.babyapp.R;

public class AllergyActivity extends AppCompatActivity {

	private TextView allergy_date, txt_confirmed_allergy, txt_suspected_allergy;
	private LinearLayout confirmed_layout, suspected_layout;
	private ImageView img_confirmed_allergy, img_suspected_allergy;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_allergy_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        allergy_date = (TextView)findViewById(R.id.allergy_date);
        txt_confirmed_allergy = (TextView)findViewById(R.id.txt_confirmed_allergy);
        txt_suspected_allergy = (TextView)findViewById(R.id.txt_suspected_allergy);
        
        img_confirmed_allergy = (ImageView)findViewById(R.id.img_confirmed_allergy);
        img_suspected_allergy = (ImageView)findViewById(R.id.img_suspected_allergy);
        
        confirmed_layout = (LinearLayout)findViewById(R.id.confirmed_layout);
        suspected_layout = (LinearLayout)findViewById(R.id.suspected_layout);
        
        confirmed_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				img_confirmed_allergy.setImageDrawable(getResources().getDrawable(R.drawable.tick_mark_primary_64px));
				txt_confirmed_allergy.setTextColor(getResources().getColor(R.color.colorPrimary));
				
				img_suspected_allergy.setImageDrawable(getResources().getDrawable(R.drawable.dash_icon_64px));
				txt_suspected_allergy.setTextColor(getResources().getColor(R.color.grey_500));
			}
		});
        suspected_layout.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				img_suspected_allergy.setImageDrawable(getResources().getDrawable(R.drawable.tick_mark_primary_64px));
				txt_suspected_allergy.setTextColor(getResources().getColor(R.color.colorPrimary));
				
				img_confirmed_allergy.setImageDrawable(getResources().getDrawable(R.drawable.dash_icon_64px));
				txt_confirmed_allergy.setTextColor(getResources().getColor(R.color.grey_500));
			}
		});

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
				allergy_date.setText(new StringBuilder().append(day)
				   .append("/").append(month + 1).append("/").append(year)
				   .append(" "));
				Toast.makeText(getApplicationContext(), ""+allergy_date.getText().toString(), Toast.LENGTH_SHORT).show();
		    }
		 }, year, month, day);

		 dpd.show();
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
	
}
