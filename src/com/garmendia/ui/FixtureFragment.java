package com.garmendia.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thegarmendiashit.R;
import com.garmendia.service.FixtureService;
import com.garmendia.ui.listeners.FixtureButtonListener;

public class FixtureFragment extends Fragment {

	private FixtureService fixtureService;
	
	private String silverCupURL;
	
	private ButtonAdapter adapter;
	
	private ProgressDialog dialog;
	
	public FixtureFragment() {
		super();
		fixtureService = new FixtureService();
		this.silverCupURL = "http://www.exacostafutbol.com/Clausura2014/Copa%20Plateada/fixture_cp.htm";
	}

	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
			View rootView = inflater.inflate(R.layout.fixture, container, false);
	         
	        ListView listView = (ListView) rootView.findViewById(R.id.fixtureListView);
	        
	        dialog = new ProgressDialog(listView.getContext());
	        
	        adapter = new ButtonAdapter(inflater.getContext(),new ArrayList<String>(),new FixtureButtonListener());
	        
	        listView.setAdapter(adapter); 
	        
	        (new HtmlServiceTask()).execute(silverCupURL);
	        
	        return rootView;
	    }
	
	
	private class HtmlServiceTask extends AsyncTask<String, Void,List<String>> {
		
		public HtmlServiceTask() {
			super();
		}

		@Override
		protected List<String> doInBackground(String... params) {
			
			fixtureService.Sync(params[0]);
			
			return fixtureService.getDates();
			
		}
		
		@Override
	    protected void onPreExecute() {       
	        super.onPreExecute();
	        dialog.setMessage("Downloading Fixture...");
	        dialog.show();   
	    }
		  
		  @Override
		protected void onPostExecute(List<String> dates) {
			super.onPostExecute(dates);
			dialog.dismiss();
			adapter.setInputList(dates);
			adapter.notifyDataSetChanged();
			
		}
	}
	
}