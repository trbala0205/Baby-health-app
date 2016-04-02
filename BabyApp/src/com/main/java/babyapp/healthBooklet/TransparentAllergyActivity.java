package com.main.java.babyapp.healthBooklet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.main.java.babyapp.R;

public class TransparentAllergyActivity extends Activity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparent_allergy);
        
	}
	
	public void callDrugAllergyPage(View view){
		startActivity(new Intent(TransparentAllergyActivity.this, DrugAllergyActivity.class));
	}
	
	public void callMedicalConditionPage(View view){
		startActivity(new Intent(TransparentAllergyActivity.this, MedicalConditionActivity.class));
	}
}
