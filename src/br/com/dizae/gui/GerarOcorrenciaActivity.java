package br.com.dizae.gui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.dizae.R;
import br.com.dizae.dao.JSONParser;
 
public class GerarOcorrenciaActivity extends Activity {
 
    // Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText inputTitulo;
    EditText inputGenero;
    EditText inputDesc;
 
    // url to create new product
    private static String url_create_product = "http://robugos.com/dizae/db/create_ocorrencia.php";
 
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerarocorrencia);
 
        // Edit Text
        inputTitulo = (EditText) findViewById(R.id.inputTitulo);
        inputGenero = (EditText) findViewById(R.id.inputGenero);
        inputDesc = (EditText) findViewById(R.id.inputDesc);
 
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
 
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String titulo_ocorrencia = inputTitulo.getText().toString();
            String genero_ocorrencia = inputGenero.getText().toString();
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
    }
}