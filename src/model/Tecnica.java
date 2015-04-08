package model;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DataBaseConnection;

public class Tecnica {
	private static final String Table = "tecnicas";
	private static final String[] colunas = new String[] { "_id" , "nome"}; 
	
	private Integer id;
	private String name;	
	
	public Tecnica(){
		
	}
	
	public Tecnica(Integer id, String nome) {
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
	
	public static ArrayList<Tecnica> selectAll(Context con) {
		
		
		SQLiteDatabase db = DataBaseConnection.connection(con);	
		
		ArrayList<Tecnica> list = new ArrayList<Tecnica>();	
		
		list.add(new Tecnica(-1,"Todos"));
		 
		Cursor c = db.query(Table, colunas, null, null, null, null, "nome ASC");
		
		if (c.moveToFirst()) {
			
			do {
				Tecnica t = new Tecnica();
				t.setId(c.getInt(c.getColumnIndex("_id")));
				t.setNome(c.getString(c.getColumnIndex("nome")));
				list.add(t);
				
			} while (c.moveToNext());
			
		}	
	    if (c != null && !c.isClosed()) {
	        c.close();
	    }		
		
		return list;
	}

	public static ArrayList<String> getArrayName(ArrayList<Tecnica> t){
		
		ArrayList<String> array =  new ArrayList<String>();
		
		Iterator<Tecnica> it = t.iterator(); 
		
		while (it.hasNext()) {  
		    array.add(it.next().getNome()); 
		}  
		
		return array;
	}

	public static ArrayList<Tecnica> selectByArtista(Context context, Artista artista) {
		SQLiteDatabase db = DataBaseConnection.connection(context);	
		
		ArrayList<Tecnica> list = new ArrayList<Tecnica>();	
		
		list.add(new Tecnica(-1,"Todos"));		
		
		//Select t._id, t.nome From tecnicas as t, artistas_tecnicas as at where at.id_artista = 28 and at.id_tecnica = t._id
		
		
		Cursor c = db.query("tecnicas as t, artistas_tecnicas as at", new String[] { "t._id, t.nome"}, "at.id_artista = " + artista.getId() + " and at.id_tecnica = t._id", null, null, null, "nome ASC");
		
		if (c.moveToFirst()) {
			
			do {
				Tecnica t = new Tecnica();
				t.setId(c.getInt(c.getColumnIndex("_id")));
				t.setNome(c.getString(c.getColumnIndex("nome")));
				list.add(t);
				
			} while (c.moveToNext());
			
		}	
	    if (c != null && !c.isClosed()) {
	        c.close();
	    }		
		
		return list;
	}
}
