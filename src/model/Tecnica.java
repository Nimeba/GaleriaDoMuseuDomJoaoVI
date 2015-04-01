package model;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DataBaseConnection;

public class Tecnica {
private static final String Table = "tecnicas";
	
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
		
		String[] colunas = new String[] { "_id" , "nome"}; 
		 
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
}
