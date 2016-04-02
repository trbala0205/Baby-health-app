package com.main.java.babyapp;

import android.app.Application;

public class BabyappApplication extends Application {

	@Override
	public void onCreate() {
		TypefaceUtil.overrideFont(getApplicationContext(), "SERIF","fonts/AvenirNextLTPro-Regular.otf"); // font from assets:"assets/fonts/Roboto-Regular.ttf
	}
}