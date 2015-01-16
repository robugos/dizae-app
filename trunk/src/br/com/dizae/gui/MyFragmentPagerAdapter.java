package br.com.dizae.gui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 3;
	
	/** Constructor of the class */
	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch(arg0){
		
			case 0:
				MinhaFragment minhas = new MinhaFragment();				
				data.putInt("current_page", arg0+1);
				minhas.setArguments(data);
				return minhas;
				
			case 1:
				MinhaFragment apoiadas = new MinhaFragment();
//				ApoiadaFragment apoiadas = new ApoiadaFragment();
				data.putInt("current_page", arg0+1);
				apoiadas.setArguments(data);
				return apoiadas;	
			
			case 2:
				MinhaFragment proximas = new MinhaFragment();
//				ProximaFragment proximas = new ProximaFragment();
				data.putInt("current_page", arg0+1);
				proximas.setArguments(data);
				return proximas;	
		}
		
		return null;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
}
