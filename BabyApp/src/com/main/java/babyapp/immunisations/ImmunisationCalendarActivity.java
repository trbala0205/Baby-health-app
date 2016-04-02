package com.main.java.babyapp.immunisations;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.java.babyapp.R;

public class ImmunisationCalendarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout calender_linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.immunisation_calendar_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        calender_linearLayout = (LinearLayout)findViewById(R.id.calendar_linearlayout);
        genarateCalendarList();
    }
    
    private void genarateCalendarList(){
    	Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		
		for(int i = 0 ; i < 5 ; i++)
		{
	    	TextView textView = new TextView(this);
	    	textView.setText("OCT 2015");
	    	textView.setTypeface(null, Typeface.BOLD);
	    	textView.setPadding(10, 10, 0, 10);
	    	textView.setTextColor(getResources().getColor(R.color.grey_600));
	    	//textView.setLayoutParams(params);
	    	
	    	calender_linearLayout.addView(textView);
	    	
	    	RelativeLayout.LayoutParams relative_params=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    	
	    	RelativeLayout relativeLayout = new RelativeLayout(this);
	    	relativeLayout.setLayoutParams(relative_params);
	    	relativeLayout.setPadding(10, 20, 10, 20);
	    	
	    	final int sdk = android.os.Build.VERSION.SDK_INT;
	    	if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
	    		relativeLayout.setBackgroundDrawable( getResources().getDrawable(R.drawable.bg_linearlayout));
	    	} else {
	    		relativeLayout.setBackgroundResource(R.drawable.bg_linearlayout);
	    	}
	    	
	    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    	
	    	LinearLayout linearLayout = new LinearLayout(this);
	    	linearLayout.setId(R.id.left_linearlayout_Id);
	    	linearLayout.setLayoutParams(params);
	    	linearLayout.setOrientation(LinearLayout.VERTICAL);
	    	linearLayout.setPadding(10, 5, 20, 0);
	    	
	    	TextView date_textView = new TextView(this);
	    	date_textView.setText("30");
	    	date_textView.setTextSize(20.0f);
	    	date_textView.setPadding(0, 5, 0, 0);
	    	if( i == 1)
	    		date_textView.setTextColor(getResources().getColor(R.color.colorPrimary));
	    	else
	    		date_textView.setTextColor(getResources().getColor(R.color.grey_600));
	    	
	    	TextView day_textView = new TextView(this);
	    	day_textView.setText("Fri");
	    	day_textView.setTextSize(16.0f);
	    	day_textView.setPadding(0, 5, 0, 0);
	    	if(i == 1)
	    		day_textView.setTextColor(getResources().getColor(R.color.colorPrimary));
	    	else
	    		day_textView.setTextColor(getResources().getColor(R.color.grey_600));
	    	
	    	linearLayout.addView(date_textView);
	    	linearLayout.addView(day_textView);
	    	
	    	relativeLayout.addView(linearLayout); //-------------------------------
	    	
	    	LinearLayout content_linearLayout = new LinearLayout(this);
	    	relative_params.addRule(RelativeLayout.RIGHT_OF, linearLayout.getId());
	    	content_linearLayout.setLayoutParams(relative_params);
	    	content_linearLayout.setOrientation(LinearLayout.VERTICAL);
	    	content_linearLayout.setPadding(10, 5, 0, 0);
	    	
	    	TextView month_textView = new TextView(this);
	    	month_textView.setText("Birth");
	    	//month_textView.setTextSize(20.0f);
	    	month_textView.setPadding(5, 5, 0, 5);
	    	if(i == 1)
	    		month_textView.setTextColor(getResources().getColor(R.color.colorPrimary));
	    	else
	    		month_textView.setTextColor(getResources().getColor(R.color.grey_600));
	    	
	    	content_linearLayout.addView(month_textView);
	    	
	    	for(int j = 0 ; j < 2 ; j++)
	    	{
		    	TextView header_textView = new TextView(this);
		    	header_textView.setText("HepB (D1)");
		    	header_textView.setTypeface(null, Typeface.BOLD);
		    	header_textView.setTextColor(getResources().getColor(R.color.grey_600));
		    	content_linearLayout.addView(header_textView);
		    	
		    	TextView sub_textView = new TextView(this);
		    	sub_textView.setText("Hepatitics B vaccine, first dose");
		    	//sub_textView.setTextSize(20.0f);
		    	sub_textView.setPadding(5, 5, 0, 10);
		    	sub_textView.setTextColor(getResources().getColor(R.color.grey_600));
		    	content_linearLayout.addView(sub_textView);
		    	
		    	if(j == 0)
		    	{
			    	LinearLayout.LayoutParams view_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
			    	View horizontalLine = new View(this);
			    	horizontalLine.setLayoutParams(view_params);
			    	horizontalLine.setBackgroundColor(getResources().getColor(R.color.grey_600));
			    	content_linearLayout.addView(horizontalLine);
		    	}
	    	}
	    	
	    	relativeLayout.addView(content_linearLayout); //-------------------------------
	    	
	    	calender_linearLayout.addView(relativeLayout);
	    	relativeLayout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					startActivity(new Intent(ImmunisationCalendarActivity.this, ImmunisationInfoActivity.class));
					
				}
			});
		}
    }
}