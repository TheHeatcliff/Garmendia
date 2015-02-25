package com.garmendia.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thegarmendiashit.R;

public class ScorersFragment extends Fragment{

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	 
	        View rootView = inflater.inflate(R.layout.goleadores, container, false);
	         
	        return rootView;
	    }
	
}


