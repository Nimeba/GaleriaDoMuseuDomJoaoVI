package com.example.galeriadomuseudomjoaovi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator; 

import model.Artista;
import model.Obra;

import database.DataBaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Galeria extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_galeria);		
		
		final ArrayList<Artista> artistas = Artista.selectAll(this);		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, Artista.getArrayName(artistas));
		
		ListView list = (ListView) findViewById(R.id.ListViewGaleria);
		list.setAdapter(adapter);
		
		final Context c = this;
		// Create a message handling object as an anonymous class.
		OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView parent, View v, int position, long id) {
		        // Do something in response to the click
				//Toast.makeText(c, "Ol‡ mundo !"+position, Toast.LENGTH_LONG).show(); 
				Obra.setArtista(artistas.get(position));
				startActivity(new Intent(Galeria.this, ExibirImagem.class));
			}		
			
		};

		list.setOnItemClickListener(mMessageClickedHandler);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.galeria, menu);
		return true;
	}

}