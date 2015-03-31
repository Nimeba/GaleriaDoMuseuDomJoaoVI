package database;

import java.io.IOException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseConnection {
		
	public static SQLiteDatabase connection(Context context) {
		
		DataBaseHelper dbhelper = new DataBaseHelper(context);  
		try {  
			dbhelper.createDataBase();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} 		
		return dbhelper.getWritableDatabase();
	}
	
}
