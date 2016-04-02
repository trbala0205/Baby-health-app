package com.main.java.babyapp.healthBooklet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.main.java.babyapp.R;

public class OralHealthActivity extends AppCompatActivity {

	private LinearLayout adult_layout, baby_layout;
	private View img_view;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oral_health_layout);
        
        adult_layout = (LinearLayout)findViewById(R.id.adult_layout);
        baby_layout = (LinearLayout)findViewById(R.id.baby_layout);
        img_view = (View)findViewById(R.id.img_view);
        adult_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final int sdk = android.os.Build.VERSION.SDK_INT;
				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
					img_view.setBackgroundDrawable( getResources().getDrawable(R.drawable.oral_health_7to8year_03) );
				} else {
					img_view.setBackground( getResources().getDrawable(R.drawable.oral_health_7to8year_03));
				}
				adult_layout.setBackgroundColor(getResources().getColor(R.color.white));
				baby_layout.setBackgroundColor(getResources().getColor(R.color.grey_300));
			}
		});
        baby_layout.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				final int sdk = android.os.Build.VERSION.SDK_INT;
				if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
					img_view.setBackgroundDrawable( getResources().getDrawable(R.drawable.oral_health_baby) );
				} else {
					img_view.setBackground( getResources().getDrawable(R.drawable.oral_health_baby));
				}
				baby_layout.setBackgroundColor(getResources().getColor(R.color.white));
				adult_layout.setBackgroundColor(getResources().getColor(R.color.grey_300));
			}
		});
	}
}
