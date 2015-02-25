package com.garmendia.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.thegarmendiashit.R;
import com.garmendia.domain.Match;
import com.garmendia.service.UpdatedFixture;
import com.garmendia.ui.listeners.FixtureButtonListener;
import com.garmendia.ui.listeners.FixtureDateButtonListener;

public class FixtureDateActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fixture_date);
		
		Intent intent = getIntent();
		String dateSelected = intent.getStringExtra(FixtureButtonListener.DATE_SELECTED);
		
		ListView listView = (ListView) findViewById(R.id.fixtureDateListView);
		
		List<Match> matches = UpdatedFixture.getInstance().getFixture().get(dateSelected);
		
		List<String> inputList = new ArrayList<String>();
		
		for (Match match : matches) {
			inputList.add(match.toString());
		}
		
		listView.setAdapter(new ButtonAdapter(this,inputList,new FixtureDateButtonListener()));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}