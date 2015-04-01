package util;

import java.util.ArrayList;

import com.example.galeriadomuseudomjoaovi.R;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ListViewAdapter extends ArrayAdapter<String>{

	private Context context;
	private String[] values;

	public ListViewAdapter(Context context, int drawerListItem, String[] values) {
		    super(context, drawerListItem, values);
		    this.context = context;
		    this.values = values;
	}
	
	/*public boolean isEnabled(int position) {
        if(values.get(position)==false)
        {
            return false;
        }
        return super.isEnabled(position);
    }*/
}
