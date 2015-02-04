package com.example.galeriadomuseudomjoaovi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Contatos extends Activity implements OnClickListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contatos);
		
		ImageButton voltar = (ImageButton) findViewById(R.id.btnVoltar);
		ImageButton agradecimentos = (ImageButton) findViewById(R.id.btnAgradecimentos);
		ImageButton contatos = (ImageButton) findViewById(R.id.btnContatos);
		ImageButton equipe = (ImageButton) findViewById(R.id.btnEquipe);
		
		
		voltar.setOnClickListener(this);
		agradecimentos.setOnClickListener(this);
		contatos.setOnClickListener(this);
		equipe.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contatos, menu);
		return true;
	}

		
	@Override
	public void onClick(View v) {
		
		ImageView imgContatos = (ImageView) findViewById(R.id.imgContatos);
		ImageView imgAgradecimentos = (ImageView) findViewById(R.id.imgAgradecimentos);
		ImageView imgEquipe = (ImageView) findViewById(R.id.imgEquipe);
		
	
		switch (v.getId()) {

			case R.id.btnAgradecimentos:
				imgAgradecimentos.setVisibility(imgAgradecimentos.VISIBLE);
				imgContatos.setVisibility(imgContatos.INVISIBLE);
				imgEquipe.setVisibility(imgEquipe.INVISIBLE);
			break;
		
			case R.id.btnContatos:
				imgContatos.setVisibility(imgContatos.VISIBLE);
				imgAgradecimentos.setVisibility(imgAgradecimentos.INVISIBLE);
				imgEquipe.setVisibility(imgEquipe.INVISIBLE);
			break;
		
			case R.id.btnEquipe:
				imgEquipe.setVisibility(imgEquipe.VISIBLE);
				imgAgradecimentos.setVisibility(imgAgradecimentos.INVISIBLE);
				imgContatos.setVisibility(imgContatos.INVISIBLE);
			break;
		
			case R.id.btnVoltar:
				startActivity(new Intent(Contatos.this, MainActivity.class));
			break;
		
			default:
				break;
		}	
	}
}

