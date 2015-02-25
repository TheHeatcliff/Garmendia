package com.garmendia.ui;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.thegarmendiashit.R;

public class GameActivity extends ActionBarActivity {

	 private ViewPager viewPager;
	 private FragmentPagerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.game_activity);
		
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction().add(R.id.ScorersFragment, new PlaceholderFragment()).commit();
//		}
		
		android.support.v7.app.ActionBar bar = getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		adapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(
	            new ViewPager.SimpleOnPageChangeListener() {
	                @Override
	                public void onPageSelected(int position) {
	                    // When swiping between pages, select the
	                    // corresponding tab.
	                    getActionBar().setSelectedNavigationItem(position);
	                }
	            });
		
		final ActionBar actionBar = getActionBar();
		
		// Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	       
	    
	 // Create a tab listener that is called when the user changes tabs.
	    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        
			ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			public void onTabUnselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
			}

			public void onTabReselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
			}
		};

	    // Add 3 tabs, specifying the tab's text and TabListener
	    actionBar.addTab(actionBar.newTab().setText("Fixture").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Goleadores").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Suspendidos").setTabListener(tabListener));	
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.game, menu);
		return true;
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

//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//			
//			View rootView = inflater.inflate(R.layout.fragment_game, container,false);
//			
//			return rootView;
//		}
//	}
}
