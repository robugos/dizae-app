package br.com.dizae.gui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.com.dizae.R;

import com.facebook.Session;

public class MainActivity extends FragmentActivity {
	
	ActionBar mActionBar;
	ViewPager mPager;
	String user_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
	    if (i.hasExtra("userID") && i.getStringExtra("userID")!=null){
	         user_ID = i.getStringExtra("userID");
	         Toast.makeText(this, "ID: "+user_ID, Toast.LENGTH_LONG).show();
	         Log.i("ID", user_ID);
	    }
		
		Button gerarNovaOcorrencia = (Button) findViewById(R.id.btnGerarNovaOcorrencia);
		gerarNovaOcorrencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	geraNovaOcorrencia();
            	}
        });
		
        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
        	@Override
        	public void onPageSelected(int position) {        		
        		super.onPageSelected(position);
        		mActionBar.setSelectedNavigationItem(position);        		
        	}        	
        };
        
        mPager.setOnPageChangeListener(pageChangeListener);
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fm);
        mPager.setAdapter(fragmentPagerAdapter);
        mActionBar.setDisplayShowTitleEnabled(true);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			@Override
			public void onTabReselected(Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onTabSelected(Tab tab,
				android.app.FragmentTransaction ft) {
				mPager.setCurrentItem(tab.getPosition());
			}
			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub	
			}
		};

        Tab tab;
        
        tab = mActionBar.newTab().setIcon(R.drawable.ic_minhas).setTabListener(tabListener);
        mActionBar.addTab(tab);

        tab = mActionBar.newTab().setIcon(R.drawable.ic_apoiadas).setTabListener(tabListener);
        mActionBar.addTab(tab);        
        
        tab = mActionBar.newTab().setIcon(R.drawable.ic_proximas).setTabListener(tabListener);                               
        mActionBar.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logoutFacebook:
			callFacebookLogout();
			return true;			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void geraNovaOcorrencia() {
		Intent i = new Intent(getApplicationContext(), GerarOcorrenciaActivity.class);
		i.putExtra("userID", user_ID);
		startActivity(i);
	}
	
	public void callFacebookLogout() {
		if (Session.getActiveSession() != null) {
		    Session.getActiveSession().closeAndClearTokenInformation();
		}

		Session.setActiveSession(null);
		Intent i = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(i);
		finish();
	}

	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_delete)
				.setTitle("Fechar")
				.setMessage("Tem certeza que deseja sair do Dizaê?")
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).setNegativeButton("Não", null).show();
	}

}
