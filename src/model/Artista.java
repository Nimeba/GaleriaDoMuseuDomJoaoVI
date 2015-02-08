package model;

import java.io.IOException;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DataBaseHelper;

public class Artista {
	
	private static final String Table = "artistas";
	
	private Integer id;
	private String nome;	
	
	public Artista(){
		
	}
	
	public Artista(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static ArrayList<Artista> selectAll(SQLiteDatabase db) {
		
		ArrayList<Artista> list = new ArrayList<Artista>();				
		
		String[] colunas = new String[] { "_id" , "nome"}; 
		 
		Cursor c = db.query("artistas", colunas, null, null, null, null, "nome ASC");
		
		if (c.moveToFirst()) {
			
			do {
				Artista a = new Artista();
				a.setId(c.getInt(c.getColumnIndex("nome")));
				a.setNome(c.getString(c.getColumnIndex("nome")));
				list.add(a);
				
			} while (c.moveToNext());
			
		}	
	    if (c != null && !c.isClosed()) {
	        c.close();
	    }		
		
		return list;
	}
}
