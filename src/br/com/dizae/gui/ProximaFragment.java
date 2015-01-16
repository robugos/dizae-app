package br.com.dizae.gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import br.com.dizae.R;
import br.com.dizae.dao.JSONParser;



public class ProximaFragment extends Fragment {

    ListView list;
    TextView id;
    TextView zip;
    TextView city;
    TextView state;
    TextView country;
    Button Btngetdata;

    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();

    //URL to get JSON Array
    private static String url = "http://dizae.robugos.com/db/get_all_ocorrencias.php";

    //JSON Node Names
    private static final String TAG_ARRAY = "ocorrencias";
    private static final String TAG_ID = "titulo_ocorrencia";
    private static final String TAG_CITY = "titulo_ocorrencia";
    private static final String TAG_ZIP = "data_ocorrencia";
    private static final String TAG_STATE = "descricao_ocorrencia";
    private static final String TAG_COUNTRY = "genero_ocorrencia";

    JSONArray cities = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_minhas, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        oslist = new ArrayList<HashMap<String, String>>();
        //Btngetdata = (Button)getView().findViewById(R.id.getdata);
        //Btngetdata.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
                new JSONParse().execute();  
            //}
        //});

    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        JSONParser jParser = new JSONParser();
        private JSONObject json;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            city = (TextView)getView().findViewById(R.id.titulo_ocorrencia);
            state = (TextView)getView().findViewById(R.id.descricao_ocorrencia);
            //country = (TextView)getView().findViewById(R.id.country);
            //id = (TextView)getView().findViewById(R.id.id);
            //zip = (TextView)getView().findViewById(R.id.zip);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();


        }

        @Override
        protected JSONObject doInBackground(String... args) {

            // Getting JSON from URL
            json = jParser.getJSONFromUrl(url);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            pDialog.dismiss();
            try {
                 // Getting JSON Array from URL
                 cities = json.getJSONArray(TAG_ARRAY);

                 for(int i = 0; i < cities.length(); i++){

                     JSONObject c = cities.getJSONObject(i);

                     // Storing  JSON item in a Variable
                     String id = c.getString(TAG_ID);
                     Log.i(id, id);
                     String zip = c.getString(TAG_ZIP);
                     String city = c.getString(TAG_CITY);
                     Log.i(city, city);
                     String state = c.getString(TAG_STATE);
                     String country = c.getString(TAG_COUNTRY);

                     // Adding value HashMap key => value
                     HashMap<String, String> map = new HashMap<String, String>();
                     map.put(TAG_ID, id);
                     map.put(TAG_ZIP, zip);
                     map.put(TAG_CITY, city);
                     map.put(TAG_STATE, state);
                     map.put(TAG_COUNTRY, country);

                     oslist.add(map);

                     list = (ListView)getView().findViewById(R.id.list);

                     ListAdapter adapter = new SimpleAdapter(getActivity(), oslist,
                         R.layout.listview_ocorrencia,
                         new String[] { TAG_CITY, TAG_STATE, TAG_COUNTRY, TAG_ID, TAG_ZIP }, new int[] {
                             R.id.titulo_ocorrencia, R.id.descricao_ocorrencia});
                     list.setAdapter(adapter);
                     list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                         @Override
                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                             Toast.makeText(getActivity(), "Você clicou em "+oslist.get(+position).get("titulo_ocorrencia"), Toast.LENGTH_SHORT).show();
                         }
                     });
                 }
            } catch (JSONException e) {
              e.printStackTrace();
            }
        }
    }
}