package br.com.dizae.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	public static final MinhasFragment MINHAS = new MinhasFragment();
	public static final ApoiadasFragment APOIADAS = new ApoiadasFragment();
	public static final ProximasFragment PROXIMAS = new ProximasFragment();
	public static final int NUM_ABAS = 3;

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// fragment_curso
			//CursoFragment.displayListView();
			return MINHAS;
		case 1:
			// fragment_predio
			return APOIADAS;
		case 2:
			// fragment_predio
			return PROXIMAS;
		}

		return null;
	}

	@Override
	public int getCount() {
		// igual numero de abas
		return NUM_ABAS;
	}

}
