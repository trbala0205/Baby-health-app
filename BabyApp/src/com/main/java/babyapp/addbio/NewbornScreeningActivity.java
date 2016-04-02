package com.main.java.babyapp.addbio;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.main.java.babyapp.R;

public class NewbornScreeningActivity extends AppCompatActivity {

	private TextView txt_date_tsh, txt_date_screening, txt_date1;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newborn_screening_layout);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        
        txt_date_tsh = (TextView)findViewById(R.id.txt_date_tsh);
        txt_date_screening = (TextView)findViewById(R.id.txt_date_screening);
        txt_date1 = (TextView)findViewById(R.id.txt_date1);
    
	}
	
	private int year;
	private int month;
	private int day;
	public void setDate(final View view1){
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
				System.out.println(view1.getId());
				if(view1.getId() == R.id.txt_date_tsh)
				{
					// set selected date into textview
					txt_date_tsh.setText(new StringBuilder().append(day)
					   .append("/").append(month + 1).append("/").append(year)
					   .append(" "));
				}else if(view1.getId() == R.id.txt_date_screening){
					// set selected date into textview
					txt_date_screening.setText(new StringBuilder().append(day)
					   .append("/").append(month + 1).append("/").append(year)
					   .append(" "));
				}else{
					// set selected date into textview
					txt_date1.setText(new StringBuilder().append(day)
					   .append("/").append(month + 1).append("/").append(year)
					   .append(" "));
				}
				//Toast.makeText(getApplicationContext(), ""+add_bio_dob.getText().toString(), Toast.LENGTH_SHORT).show();
		    }
		 }, year, month, day);

		 dpd.show();
    }
	
	public void callPreviousPage(View view){
		startActivity(new Intent(NewbornScreeningActivity.this, ParticularsOfParentsActivity.class));
		finish();
	}
	
	public void callNextPage(View view){
		startActivity(new Intent(NewbornScreeningActivity.this, InverstigationsActivity.class));
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toolbar_actions, menu);
		
		MenuItem action_done = menu.findItem(R.id.action_done);
		action_done.setVisible(true);
		return true;
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
        case R.id.action_done:
    		return true;
    }
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    public void onBackPressed() {
        super.onBackPressed();
    }
	
}