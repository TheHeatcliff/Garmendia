package com.garmendia.ui.listeners;

import android.content.Intent;
import android.view.View;

import com.garmendia.ui.FixtureDateActivity;

public class FixtureDateButtonListener implements OnPositionclickListener{
	
	private int position;
	
	public FixtureDateButtonListener() {
		super();
	}
	

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(v.getContext(),FixtureDateActivity.class);
		intent.putExtra("dateSelected",position);
		v.getContext().startActivity(intent);
	}


	@Override
	public void setPosition(int position) {
		this.position = position;
	}
}
