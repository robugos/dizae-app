package br.com.dizae.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import br.com.dizae.R;
import br.com.dizae.dao.JSONParser;

public class AllOcorrenciasActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> ocorrenciasList;

	// url to get all ocorrencias list
	private static String url_all_ocorrencias = "http://dizae.robugos.com/db/get_all_ocorrencias.php";

	// JSON Node titulo_ocorrencias
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_OCORRENCIAS = "ocorrencias";
	private static final String TAG_ID_OCORRENCIA = "id_ocorrencia";
	private static final String TAG_TITULO_OCORRENCIA = "titulo_ocorrencia";

	// ocorrencias JSONArray
	JSONArray ocorrencias = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.minhas_ocorrencias);

		// Hashmap for ListView
		ocorrenciasList = new ArrayList<HashMap<String, String>>();

		// Loading ocorrencias in Background Thread
		new LoadAllOcorrencias().execute();

		// Get listview
		ListView lv = getListView();

		// on seleting single ocorrencia
		// launching Edit Ocorrencia Screen
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			/*	// getting values from selected ListItem
				String id_ocorrencia = ((TextView) view.findViewById(R.id.pid)).getText()
						.toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						EditOcorrenciaActivity.class);
				// sending id_ocorrencia to next activity
				in.putExtra(TAG_ID_OCORRENCIA, id_ocorrencia);
				
				// starting new activity and expecting some response back
				startActivityForResult(in, 100);*/
			}
		});

	}

	// Response from Edit Ocorrencia Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted ocorrencia
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all ocorrencia by making HTTP Request
	 * */
	class LoadAllOcorrencias extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(AllOcorrenciasActivity.this);
			pDialog.setMessage("Carregando ocorrencias. Aguarde por favor...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All ocorrencias from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_ocorrencias, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("Todas as Ocorrencias: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// ocorrencias found
					// Getting Array of Ocorrencias
					ocorrencias = json.getJSONArray(TAG_OCORRENCIAS);

					// looping through All Ocorrencias
					for (int i = 0; i < ocorrencias.length(); i++) {
						JSONObject c = ocorrencias.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_ID_OCORRENCIA);
						String titulo_ocorrencia = c.getString(TAG_TITULO_OCORRENCIA);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_ID_OCORRENCIA, id);
						map.put(TAG_TITULO_OCORRENCIA, titulo_ocorrencia);

						// adding HashList to ArrayList
						ocorrenciasList.add(map);
					}
				} else {
					// no ocorrencias found
					// Launch Add New ocorrencia Activity
		/*			Intent i = new Intent(getApplicationContext(),
							NewOcorrenciaActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i); */
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
			// dismiss the dialog after getting all ocorrencias
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							AllOcorrenciasActivity.this, ocorrenciasList,
							R.layout.listview_minhas, new String[] { TAG_ID_OCORRENCIA,
									TAG_TITULO_OCORRENCIA},
							new int[] { R.id.pid, R.id.name });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}