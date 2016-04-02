package com.main.java.babyapp.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
	public CustomTextView(Context context) {
		super(context);
		setNormalFont();
	}
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setNormalFont();
	}
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setNormalFont();
	}

	private void setNormalFont() {
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
		setTypeface(font); //setTypeface(font, Typeface.NORMAL);
	}
	
	private void setBoldFont() {
		Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/AvenirNextLTPro-Bold.otf");
		setTypeface(font); //setTypeface(font, Typeface.BOLD);
	}
}