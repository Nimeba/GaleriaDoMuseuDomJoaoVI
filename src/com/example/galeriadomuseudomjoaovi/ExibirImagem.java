package com.example.galeriadomuseudomjoaovi;

import model.Obra;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class ExibirImagem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibir_imagem);
		ImageView img = (ImageView) findViewById(R.id.imageViewObra);
		Obra selectedObra = Obra.selectObra(this);
		String s = "img_galeria_" + selectedObra.getId();
		int imageId = getResources().getIdentifier(s, "drawable", getPackageName());
		img.setImageResource(imageId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exibir_imagem, menu);
		return true;
	}

}
