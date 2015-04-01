package model;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DataBaseConnection;

public class Obra {
	
	private static final String Table = "galeria";	
	static Artista artista;
	
	private Integer id;
	private String obra;	
	
	public Obra(){
		
	}
	
	public Obra(Integer id, String obra) {
		this.id = id;
		this.obra = obra;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObra() {
		return obra;
	}

	public void setObra(String obra) {
		this.obra = obra;
	}

	public static Artista getArtista() {
		return artista;
	}

	public static void setArtista(Artista artista) {
		Obra.artista = artista;
	}
	
	public static ArrayList<Obra> selectAll(Context con) {
		
		
		SQLiteDatabase db = DataBaseConnection.connection(con);	
		
		ArrayList<Obra> list = new ArrayList<Obra>();	
		
		list.add(new Obra(-1,"Todos"));
		
		String[] colunas = new String[] { "_id" , "obra"}; 
		 
		Cursor c = db.query(Table, colunas, null, null, null, null, "obra ASC");
		
		if (c.moveToFirst()) {
			
			do {
				Obra o = new Obra();
				o.setId(c.getInt(c.getColumnIndex("_id")));
				o.setObra(c.getString(c.getColumnIndex("obra")));
				list.add(o);
				
			} while (c.moveToNext());
			
		}	
	    if (c != null && !c.isClosed()) {
	        c.close();
	    }		
		
		return list;
	}
	

	public static ArrayList<String> getArrayName(ArrayList<Obra> o){
		
		ArrayList<String> array =  new ArrayList<String>();
		
		Iterator<Obra> it = o.iterator(); 
		
		while (it.hasNext()) {  
		    array.add(it.next().getObra()); 
		}  
		
		return array;
	}
}
