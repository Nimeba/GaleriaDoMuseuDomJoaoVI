package util;

import java.util.ArrayList;

import com.example.galeriadomuseudomjoaovi.R;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

public class ListViewAdapter extends ArrayAdapter<String>{

	private Context context;
	private String[] values;
	private static ArrayList<Boolean> clickable = new ArrayList<Boolean>();

	public ListViewAdapter(Context context, int drawerListItem, String[] values) {
		    super(context, drawerListItem, values);
		    this.context = context;
		    this.values = values;
		    this.setClickable(values);
	}
	
	public void setClickable(String[] values) {
		for (int i = 0; i < values.length; i++) {
			clickable.add(true);
		}
	}
	
	public void setEnable(int position){
		clickable.set(position, false);
	}
	
	@Override
	public boolean isEnabled(int position) {
        if(!clickable.get(position))
        {
            return false;
        }
        return super.isEnabled(position);
    }
}
