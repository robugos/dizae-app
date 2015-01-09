package br.com.dizae.gui;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.com.dizae.R;

import com.facebook.AppEventsLogger;
 
public class SplashScreen extends Activity {
 
    // Tempo da splash
    private static int SPLASH_TIME_OUT = 1000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
	    /*try {
	    	Log.e("Entrou no TRY", "try");
	        PackageInfo info = getPackageManager().getPackageInfo(
	                "br.com.dizae", 
	                PackageManager.GET_SIGNATURES);
	        Log.e("Antes do FOR", "FOR");
	        for (Signature signature : info.signatures) {
	        	Log.e("Dentro do FOR", "FOR");
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            Log.e("Antes da HASH", "HASH");
	            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	            Log.e("Depois da HASH", "HASH");
	            }
	        Log.e("Fora do FOR", "FOR");
	    } catch (NameNotFoundException e) {
	    	Log.e("NameNotFoundException", "");

	    } catch (NoSuchAlgorithmException e) {
	    	Log.e("NoSuchAlgorithmException", "");

	    }
	    Log.e("Saiu do TRY", "try");)*/
 
        new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                // Chama a Main quando acabar o tempo
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
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
 
}