package com.garmendia.ui.listeners;

import android.content.Intent;
import android.view.View;

import com.garmendia.ui.FixtureDateActivity;

public class FixtureButtonListener implements OnPositionclickListener{

	private int position;
	
	public static final String DATE_SELECTED = "DATE_SELECTED";
	
	public FixtureButtonListener() {
		super();
	}
	
	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent(view.getContext(),FixtureDateActivity.class);
		
		intent.putExtra(DATE_SELECTED,position);
		
		view.getContext().startActivity(intent);
	}

	@Override
	public void setPosition(int position) {
		this.position = position;
	}
	
	
}