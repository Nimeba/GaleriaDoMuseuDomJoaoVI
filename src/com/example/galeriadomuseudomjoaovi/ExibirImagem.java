package com.example.galeriadomuseudomjoaovi;

import model.Obra;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Gallery;
import android.widget.Toast;

public class ExibirImagem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibir_imagem);
		Toast.makeText(this, Obra.getArtista().getNome(), Toast.LENGTH_LONG).show(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exibir_imagem, menu);
		return true;
	}

}
