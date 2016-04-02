package com.main.java.babyapp.healthBooklet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.main.java.babyapp.HealthBookletActivity;
import com.main.java.babyapp.R;

public class TransparentOralVisualActivity extends Activity{

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transparent_oral_visual);
        
	}
	
	public void callOralHealthPage(View view){
		startActivity(new Intent(TransparentOralVisualActivity.this, OralHealthActivity.class));
	}
}
