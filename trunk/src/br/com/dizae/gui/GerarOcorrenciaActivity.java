package br.com.dizae.gui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.dizae.R;
import br.com.dizae.dao.JSONParser;
 
public class GerarOcorrenciaActivity extends Activity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    Spinner inputGenero;
    EditText inputTitulo;
    EditText inputDesc;
    
    LocationManager lm;
    TextView lt, ln;
    String provider;
    Location l;
 
    // url to create new product
    private static String url_create_product = "http://robugos.com/dizae/db/create_ocorrencia.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
 
    @SuppressLint("SimpleDateFormat")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerarocorrencia);
        
        ln=(TextView)findViewById(R.id.longitude);
        lt=(TextView)findViewById(R.id.latitude);
        //get location service
        lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        Criteria c=new Criteria();
        provider=lm.getBestProvider(c, false);
        
        l=lm.getLastKnownLocation(provider);
        if(l!=null){
          //get latitude and longitude of the location
          double lng=l.getLongitude();
          double lat=l.getLatitude();
          //display on text view
          ln.setText(""+lng);
          lt.setText(""+lat);
        }else{
         ln.setText("No Provider");
         lt.setText("No Provider");
        }

 
        inputGenero = (Spinner)findViewById(R.id.inputGenero);
        ArrayAdapter<String> generoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        generoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputGenero.setAdapter(generoAdapter);
        generoAdapter.add("Teste");
        generoAdapter.notifyDataSetChanged();
 
        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnGerarOcorrencia);
        
 
        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new GerarNovaOcorrencia().execute();
            }
        });
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
		    NavUtils.navigateUpFromSameTask(this);
		    return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
 
    /**
     * Background Async Task to Create new product
     * */
    class GerarNovaOcorrencia extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(GerarOcorrenciaActivity.this);
            pDialog.setMessage("Gerando ocorrência..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        
        protected String doInBackground(String... args) {
            //Elementos da XML
            inputTitulo = (EditText) findViewById(R.id.inputTitulo);
            inputDesc = (EditText) findViewById(R.id.inputDesc);
            String titulo_ocorrencia = inputTitulo.getText().toString();
            String genero_ocorrencia = inputGenero.getSelectedItem().toString();;
            
            String descricao_ocorrencia = inputDesc.getText().toString();
 
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("titulo_ocorrencia", titulo_ocorrencia));
            params.add(new BasicNameValuePair("genero_ocorrencia", genero_ocorrencia));
            params.add(new BasicNameValuePair("descricao_ocorrencia", descricao_ocorrencia));
 
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product, params);
 
            // check log cat fro response
            Log.d("Create Response", json.toString());
 
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
 
                    // closing this screen
                	finish();

                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }

		/**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
        
        public void onLocationChanged(Location arg0)
        {
         double lng=l.getLongitude();
         double lat=l.getLatitude();
         ln.setText(""+lng);
         lt.setText(""+lat);
        }

       public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub
       }
       public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub
       }

       public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub
       }
    }
}