package util.screenslide;

import com.example.galeriadomuseudomjoaovi.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScreenSlidePageFragment extends Fragment{

	public int position; 	
	public ScreenSlidePageFragment(int position) {
		this.position = position;
	}

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		ViewGroup rootView;
		switch (this.position) {
			case 0:
				rootView = (ViewGroup) inflater.inflate(
		                R.layout.itemmenugaleria, container, false);
				break;
				
			case 1:
				rootView = (ViewGroup) inflater.inflate(
		                R.layout.itemmenucontato, container, false);
				break;
				
			case 2:
				rootView = (ViewGroup) inflater.inflate(
		                R.layout.itemmenucomochegar, container, false);
				break;
				
			case 3:
				rootView = (ViewGroup) inflater.inflate(
		                R.layout.itemmenutour, container, false);
				break;	
				
			default:
				rootView = (ViewGroup) inflater.inflate(
		                R.layout.itemmenugaleria, container, false);
				break;
		}
		
	       
	     return rootView;
	 }
}
