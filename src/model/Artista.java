package model;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DataBaseConnection;

public class Artista {
	
	private static final String Table = "artistas";
	
	private Integer id;
	private String name;	
	
	public Artista(){
		
	}
	
	public Artista(Integer id, String nome) {
		this.id = id;
		this.name = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return name;
	}
	public void setNome(String nome) {
		this.name = nome;
	}
	
	public static ArrayList<Artista> selectAll(Context con) {
		
		
		SQLiteDatabase db = DataBaseConnection.connection(con);	
		
		ArrayList<Artista> list = new ArrayList<Artista>();	
		
		list.add(new Artista(-1,"Todos"));
		
		String[] colunas = new String[] { "_id" , "nome"}; 
		 
		Cursor c = db.query(Table, colunas, null, null, null, null, "nome ASC");
		
		if (c.moveToFirst()) {
			
			do {
				Artista a = new Artista();
				a.setId(c.getInt(c.getColumnIndex("_id")));
				a.setNome(c.getString(c.getColumnIndex("nome")));
				list.add(a);
				
			} while (c.moveToNext());
			
		}	
	    if (c != null && !c.isClosed()) {
	        c.close();
	    }		
		
		return list;
	}

	public static ArrayList<String> getArrayName(ArrayList<Artista> a){
		
		ArrayList<String> array =  new ArrayList<String>();
		
		Iterator<Artista> it = a.iterator(); 
		
		while (it.hasNext()) {  
		    array.add(it.next().getNome()); 
		}  
		
		return array;
	}
}