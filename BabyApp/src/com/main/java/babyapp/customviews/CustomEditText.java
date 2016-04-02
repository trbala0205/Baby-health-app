package com.main.java.babyapp.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {
	public CustomEditText(Context context) {
		super(context);
		setNormalFont();
	}
	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setNormalFont();
	}
	public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setNormalFont();
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