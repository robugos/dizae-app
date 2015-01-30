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
import br.com.dizae.R;

import com.facebook.Session;

public class MainActivity extends FragmentActivity {
	
	ActionBar mActionBar;
	ViewPager mPager;
	//String user_ID = getIntent().getStringExtra("id");
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button gerarNovaOcorrencia = (Button) findViewById(R.id.btnGerarNovaOcorrencia);
		//Toast.makeText(this,"ID: "+user_ID,Toast.LENGTH_LONG).show();
		//Log.e("id", user_ID);
		 
        // button click event
		gerarNovaOcorrencia.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // creating new product in background thread
            	geraNovaOcorrencia();
            	}
        });
		
		/** Getting a reference to action bar of this activity */
        mActionBar = getActionBar();
        
        /** Set tab navigation mode */
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        /** Getting a reference to ViewPager from the layout */
        mPager = (ViewPager) findViewById(R.id.pager);
        
        /** Getting a reference to FragmentManager */
        FragmentManager fm = getSupportFragmentManager();
        
        /** Defining a listener for pageChange */
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
        	@Override
        	public void onPageSelected(int position) {        		
        		super.onPageSelected(position);
        		mActionBar.setSelectedNavigationItem(position);        		
        	}        	
        };
        
        /** Setting the pageChange listner to the viewPager */
        mPager.setOnPageChangeListener(pageChangeListener);
        
        /** Creating an instance of FragmentPagerAdapter */
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fm);
        
        /** Setting the FragmentPagerAdapter object to the viewPager object */
        mPager.setAdapter(fragmentPagerAdapter);

        mActionBar.setDisplayShowTitleEnabled(true);
        
        /** Defining tab listener */
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

		/** Creating Android Tab */
        Tab tab = mActionBar.newTab()
                           //.setText("Minhas")
                           .setIcon(R.drawable.ic_minhas)
                           .setTabListener(tabListener);
        
        mActionBar.addTab(tab);

        /** Creating Apple Tab */
        tab = mActionBar.newTab()
                       //.setText("Apoiadas")
                       .setIcon(R.drawable.ic_apoiadas)
                       .setTabListener(tabListener);                               

        mActionBar.addTab(tab);        
        
        tab = mActionBar.newTab()
                       //.setText("Próximas")
                       .setIcon(R.drawable.ic_proximas)
                       .setTabListener(tabListener);                               

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
		/**case R.id.novaOcorrencia:
			geraNovaOcorrencia();
			return true;**/
		case R.id.logoutFacebook:
			callFacebookLogout();
			return true;			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void geraNovaOcorrencia() {
		Intent i = new Intent(getApplicationContext(), GerarOcorrenciaActivity.class);
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
