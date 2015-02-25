package com.garmendia.ui;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.thegarmendiashit.R;
import com.garmendia.ui.listeners.OnPositionclickListener;

public class ButtonAdapter extends BaseAdapter{

	private Context context; 
	
	private List<String>inputs;
	
	OnPositionclickListener buttonListener;
	
	public ButtonAdapter(Context c,List<String> inputMatches,OnPositionclickListener buttonListener) {
		this.context = c;
		this.inputs = inputMatches;
		this.buttonListener = buttonListener;
	}
	
	public List<String> getInputList(){
		return inputs;
	}
	
	public void setInputList(List<String> inputMatches) {
		this.inputs = inputMatches;
	}

	@Override
	public int getCount() {
		return inputs.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Button button;
		
		if (convertView == null) {    
			   // if it's not recycled, initialize some attributes  
			button = new Button(context);  
			button.setWidth(LayoutParams.MATCH_PARENT);
			button.setGravity(Gravity.CENTER);
  
		} else {
			button = (Button) convertView;
		}
		
		String input = inputs.get(position).toString();
		button.setText(input.toString());
		button.setTextColor(Color.WHITE);  
		button.setBackgroundResource(R.drawable.button_custom);
		button.setId(position);
		buttonListener.setPosition(position);
		button.setOnClickListener(buttonListener);
//		button.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
//		button.setGravity(View.TEXT_ALIGNMENT_VIEW_END);
		return button;
	}
}