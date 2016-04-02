package com.main.java.babyapp.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
	public CustomButton(Context context) {
		super(context);
		setBoldFont();
	}
	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBoldFont();
	}
	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setBoldFont();
	}

	private void setNormalFont() {
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeueCyr-Roman.otf");
		setTypeface(font); //setTypeface(font, Typeface.NORMAL);
	}
	
	private void setBoldFont() {
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
		setTypeface(font); //setTypeface(font, Typeface.BOLD);
	}
}