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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import br.com.dizae.R;
import br.com.dizae.dao.JSONParser;



public class MinhaFragment extends Fragment {

    ListView list;
    TextView id_ocorrencia;
    TextView titulo_ocorrencia;
    TextView descricao_ocorrencia;
    TextView genero_ocorrencia;
    TextView data_ocorrencia;

    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();

    //URL to get JSON Array
    private static String url = "http://dizae.robugos.com/db/get_all_ocorrencias.php";

    //JSON Node Names
    private static final String TAG_ARRAY = "ocorrencias";
    private static final String TAG_ID = "titulo_ocorrencia";
    private static final String TAG_TITULO= "titulo_ocorrencia";
    private static final String TAG_DATA = "data_ocorrencia";
    private static final String TAG_DESCRICAO = "descricao_ocorrencia";
    private static final String TAG_GENERO = "genero_ocorrencia";

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
            titulo_ocorrencia = (TextView)getView().findViewById(R.id.titulo_ocorrencia);
            descricao_ocorrencia = (TextView)getView().findViewById(R.id.descricao_ocorrencia);
            //genero_ocorrencia = (TextView)getView().findViewById(R.id.genero_ocorrencia);
            //id_ocorrencia = (TextView)getView().findViewById(R.id.id_ocorrencia);
            //data_ocorrencia = (TextView)getView().findViewById(R.id.data_ocorrencia);

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Carregando lista ...");
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
                     String id_ocorrencia = c.getString(TAG_ID);
                     String data_ocorrencia = c.getString(TAG_DATA);
                     String titulo_ocorrencia = c.getString(TAG_TITULO);
                     String descricao_ocorrencia = c.getString(TAG_DESCRICAO);
                     String genero_ocorrencia = c.getString(TAG_GENERO);

                     // Adding value HashMap key => value
                     HashMap<String, String> map = new HashMap<String, String>();
                     map.put(TAG_ID, id_ocorrencia);
                     map.put(TAG_DATA, data_ocorrencia);
                     map.put(TAG_TITULO, titulo_ocorrencia);
                     map.put(TAG_DESCRICAO, descricao_ocorrencia);
                     map.put(TAG_GENERO, genero_ocorrencia);

                     oslist.add(map);

                     list = (ListView)getView().findViewById(R.id.list);

                     ListAdapter adapter = new SimpleAdapter(getActivity(), oslist,
                         R.layout.listview_ocorrencia,
                         new String[] { TAG_TITULO, TAG_DESCRICAO, TAG_GENERO, TAG_ID, TAG_DATA }, new int[] {
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
            	pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Sem conexão com o servidor");
                pDialog.setIndeterminate(true);
                pDialog.setCancelable(false);
                pDialog.show();
              e.printStackTrace();
            }
        }
    }
}