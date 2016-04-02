package com.main.java.babyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.main.java.babyapp.healthBooklet.TransparentAllergyActivity;
import com.main.java.babyapp.healthBooklet.TransparentMainActivity;
import com.main.java.babyapp.healthBooklet.TransparentOralVisualActivity;
import com.main.java.babyapp.immunisations.ImmunisationCalendarActivity;
import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;

public class HealthBookletActivity  extends AppCompatActivity {

	private CircleMenuLayout mCircleMenuLayout;
	//Oral Health and Visual , Allergy and MedicalCondition
	private String[] mItemTexts = new String[] { "Immunisation", "Percentiles", "Oral Health and Visual", "Allergy and Medical Condition", "Screening"};
	private int[] mItemImgs = new int[] { R.drawable.no_health_booklet_menu_new2_03,R.drawable.health_booklet_percentiles, R.drawable.health_booklet_oral_health_and_visual,R.drawable.health_booklet_allergy_medical_condition, R.drawable.no_health_booklet_menu_new2_03 };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_booklet_layout);
        
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_circlelayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		
		mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener()
	    {
			@Override
			public void itemClick(View view, int pos)
			{
				//Toast.makeText(HealthBookletActivity.this, mItemTexts[pos], Toast.LENGTH_SHORT).show();
				if(pos == 0)
					startActivity(new Intent(HealthBookletActivity.this, ImmunisationCalendarActivity.class));
				//else if(pos == 1)
					//startActivity(new Intent(HealthBookletActivity.this, TransparentOralVisualActivity.class));
				else if(pos == 2)
					startActivity(new Intent(HealthBookletActivity.this, TransparentOralVisualActivity.class));
				else if(pos == 3)
					startActivity(new Intent(HealthBookletActivity.this, TransparentAllergyActivity.class));
				else if(pos == 4)
					startActivity(new Intent(HealthBookletActivity.this, ScreeningsActivity.class));

			}
			
			@Override
			public void itemCenterClick(View view)
			{
				//Toast.makeText(HealthBookletActivity.this,"you can do something just like ccb  ",Toast.LENGTH_SHORT).show();
				startActivity(new Intent(HealthBookletActivity.this, TransparentMainActivity.class));
				
			}
		});
	}
}
