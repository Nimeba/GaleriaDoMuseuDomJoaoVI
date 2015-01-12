package util.screenslide;

import com.example.galeriadomuseudomjoaovi.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScreenSlidePageFragment extends Fragment{

	/*private int position; 
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}*/
	 public static final String ARG_PAGE = "page";
	 
	 private int mPageNumber;
	 
	 public static ScreenSlidePageFragment create(int pageNumber) {
	        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
	        Bundle args = new Bundle();
	        args.putInt(ARG_PAGE, pageNumber);
	        fragment.setArguments(args);
	        return fragment;
	    }
	
	public ScreenSlidePageFragment(){
		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		ViewGroup rootView;
		
		switch (this.mPageNumber) {
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
		                R.layout.itemmenucomochegar, container, false);
				break;
		}
		
	     return rootView;
	 }
	
	  public int getPageNumber() {
	        return mPageNumber;
	  }
}
