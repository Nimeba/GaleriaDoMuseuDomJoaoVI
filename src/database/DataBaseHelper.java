package database;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db_mdjvi.sql";  
	private final Context context;  
	private String DB_PATH; 
	
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;  
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void createDataBase() throws IOException {  

		boolean dbExist = checkDataBase();  
		if (dbExist) {  
			Log.d("SQL","dbExists!");
		} else {  
			this.getReadableDatabase();  
			try {  
				copyDataBase();  
			} catch (IOException e) {  
				throw new Error("Error copying database");  
			}  
		}  
	}  

	private boolean checkDataBase() {  
		File dbFile = new File(DB_PATH + DATABASE_NAME);  
		return dbFile.exists();  
	}  

	private void copyDataBase() throws IOException {  

		InputStream myInput = context.getAssets().open(DATABASE_NAME);  
		String outFileName = DB_PATH + DATABASE_NAME;  
		OutputStream myOutput = new FileOutputStream(outFileName);  
		byte[] buffer = new byte[1024];  
		int length;  
		while ((length = myInput.read(buffer)) > 0) {  
			myOutput.write(buffer, 0, length);  
		}  

		// Close the streams  
		myOutput.flush();  
		myOutput.close();  
		myInput.close();  

	}  
}
