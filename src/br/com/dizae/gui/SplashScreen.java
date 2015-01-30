package br.com.dizae.gui;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import br.com.dizae.R;

import com.facebook.AppEventsLogger;
import com.facebook.Session;
 
public class SplashScreen extends Activity {
 
    // Tempo da splash
    private static int SPLASH_TIME_OUT = 1000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        //Neste try é onde ocorre a captura da hash key:
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "Your package name", 
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Your Tag", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
	    Log.e("Saiu do TRY", "try");
 
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                // Chama a Main quando acabar o tempo
            	Intent i = null;
            	i = new Intent(SplashScreen.this, LoginActivity.class);
            	startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    
    @Override
    protected void onResume() {
      super.onResume();

      // Logs 'install' and 'app activate' App Events.
      AppEventsLogger.activateApp(this);
    }
    
    @Override
    protected void onPause() {
      super.onPause();

      // Logs 'app deactivate' App Event.
      AppEventsLogger.deactivateApp(this);
    }
    
    public boolean isLoggedIn() {
        Session session = Session.getActiveSession();
        return (session != null && session.isOpened());     
    }
}