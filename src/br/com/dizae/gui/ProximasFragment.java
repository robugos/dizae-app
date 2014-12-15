package br.com.dizae.gui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import br.com.dizae.R;
import br.com.dizae.dominio.Apoiada;

@SuppressLint("InflateParams")
public class ProximasFragment extends Fragment {

	ListAdapter dataAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View principal = inflater.inflate(R.layout.fragment_apoiada, container,
				false);

		// View de todos os predios
		//displayListView(principal);

		return principal;
	}

	/*private void displayListView(View view) {

		ArrayList<Apoiada> predioList = new ArrayList<Apoiada>();
		ApoiadaBS predioBS = new ApoiadaBS();
		Log.i("resposta: ", "antes de strings[]");
		String[] parts = predioBS.pegarPredios().split(",");
		Log.i("resposta: ", "depois de strings[]");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split("#");
			String id = c[0];
			Log.i("resposta: ", id);
			String nome = c[1];
			Log.i("resposta: ", nome);
			String quantAndar = c[2];
			Log.i("resposta: ", quantAndar);
			Apoiada predio = new Apoiada(id, nome, quantAndar, false);
			predioList.add(predio);
		}

		dataAdapter = new ListAdapter(getActivity(), R.layout.listview_apoiada,
				predioList);
		ListView listView = (ListView) view.findViewById(R.id.listviewPredio);
		//
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Apoiada apoiada = (Apoiada) parent.getItemAtPosition(position);
				//chamaPredio(apoiada);
			}
		});

	}*/

	private class ListAdapter extends ArrayAdapter<Apoiada> {

		private ArrayList<Apoiada> predioList;

		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Apoiada> predioList) {
			super(context, textViewResourceId, predioList);
			this.predioList = new ArrayList<Apoiada>();
			this.predioList.addAll(predioList);
		}

		private class ViewHolder {
			TextView nome;
			TextView quantAndar;
			CheckBox favorito;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listview_apoiada, null);

				holder = new ViewHolder();
				holder.nome = (TextView) convertView.findViewById(R.id.nome);
				holder.quantAndar = (TextView) convertView.findViewById(R.id.quantAndar);
				holder.favorito = (CheckBox) convertView
						.findViewById(R.id.favoritar);
				//holder.favorito.setButtonDrawable(R.drawable.star);
				convertView.setTag(holder);

				holder.favorito.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox fav = (CheckBox) v;
						Apoiada predio = (Apoiada) fav.getTag();
						//FavoritoBS favorito = new FavoritoBS();
						if (fav.isChecked() == true) {
							//favorito.favoritar("predio", predio.getId());
							Toast.makeText(
									getContext().getApplicationContext(),
									fav.getId() + " agora é um favorito.",
									Toast.LENGTH_LONG).show();
						} else {
							//favorito.favoritar("predio", predio.getId());
							Toast.makeText(
									getContext().getApplicationContext(),
									fav.getId() + " não é mais um favorito.",
									Toast.LENGTH_LONG).show();
						}
						predio.setSelected(fav.isChecked());
					}
				});

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Apoiada predio = predioList.get(position);
			holder.nome.setText(predio.getNome());
			holder.quantAndar.setText(predio.getQuantAndar());
			holder.favorito.setId(position);
			holder.favorito.setChecked(predio.isSelected());
			holder.favorito.setTag(predio);

			return convertView;

		}

	}

	/*public void chamaPredio(Predio predio) {

		Intent i = new Intent(getActivity(), SalaActivity.class);
		Bundle bundlePredio = new Bundle();
		bundlePredio.putString("id", predio.getId());
		Log.i("id", predio.getId());
		bundlePredio.putString("nome", predio.getNome());
		Log.i("nome", predio.getNome());
		bundlePredio.putString("quantAndar", predio.getQuantAndar());
		Log.i("turno", predio.getQuantAndar());
		i.putExtras(bundlePredio);
		startActivity(i);
	}*/

}
