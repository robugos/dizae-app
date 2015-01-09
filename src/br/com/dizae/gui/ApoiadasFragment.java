package br.com.dizae.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.dizae.R;

public class ApoiadasFragment extends Fragment {
	
	Button mostrarOcorrencias;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View principal = inflater.inflate(R.layout.fragment_minhas, container,
				false);
		
		mostrarOcorrencias = (Button) principal.findViewById(R.id.allOcorrencias);
		mostrarOcorrencias.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),	AllOcorrenciasActivity.class));
			}
		});

		return principal;
	}
}
