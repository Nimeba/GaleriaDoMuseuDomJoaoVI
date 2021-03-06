/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.galeriadomuseudomjoaovi;

import java.util.ArrayList;
import java.util.Locale;

import model.*;
import util.App;
import util.ListViewAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.drm.DrmStore.RightsStatus;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
@SuppressLint("NewApi")
public class NavDrawerMainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navdrawer_activity_main);
        
        App.setContext(this);
        
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.menu_galeria_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ListViewAdapter(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        return super.onOptionsItemSelected(item);
        
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ListViewFragment.ARG_LIST_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
      
    
    public class ListViewFragment extends Fragment {

    	public static final String ARG_LIST_NUMBER = "planet_number";
    	
    	public ListViewFragment() {
    		// TODO Auto-generated constructor stub
    	}
    	
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int itemmenu = getArguments().getInt(ARG_LIST_NUMBER);
            ArrayList<String> array =  new ArrayList<String>();
            switch (itemmenu) {
			case 1: 
				ArrayList<Tecnica> tecnicas = new ArrayList<Tecnica>(); 
				if(Obra.getArtista()==null){
					tecnicas = Tecnica.selectAll(App.getContext());					
				}
				else{
					tecnicas = Tecnica.selectByArtista(App.getContext(), Obra.getArtista());
				}
				array = Tecnica.getArrayName(tecnicas);
				break;
				
			case 2: 
				ArrayList<Obra> obras = Obra.selectAll(App.getContext());
				array = Obra.getArrayName(obras);
				break;
			
			case 3: 
				array.add("Favoritos");
				break;	

			default:
				ArrayList<Artista> artistas = Artista.selectAll(App.getContext());
				array = Artista.getArrayName(artistas);
				break;
			}           
            
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(App.getContext(),
    		        android.R.layout.simple_list_item_1, array);
    		
            ListView list = (ListView) rootView.findViewById(R.id.ListViewGaleria);
    		list.setAdapter(adapter);
    		list.setOnItemClickListener(selectItemListView);
    		
            return rootView;
        }      
        
        OnItemClickListener selectItemListView = new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView parent, View v, int position, long id) {
    	        // Do something in response to the click
    			int itemmenu = getArguments().getInt(ARG_LIST_NUMBER);
    			ListView list = (ListView) findViewById(R.id.left_drawer);
    			if (itemmenu == 0) {
    				ArrayList<Artista> artistas = Artista.selectAll(App.getContext());
    				mPlanetTitles[0] = artistas.get(position).getNome(); 
    				//C�digo para mudar o texto do item menu lateral. Fazer para o t�cnicas TO DO
    						
				}
    			  
    			ListViewAdapter adapter = new ListViewAdapter(App.getContext(), R.layout.drawer_list_item, mPlanetTitles);
    			adapter.setEnable(itemmenu);
    			adapter.isEnabled(itemmenu);
    			list.setAdapter(adapter);
    			mDrawerLayout.openDrawer(Gravity.LEFT); 
    			setObra(itemmenu, position);
    		}		
    		
    	};
    	
    	public void setObra(int itemmenu, int position){
    		switch (itemmenu) {
			case 1: 
				ArrayList<Tecnica> tecnicas = Tecnica.selectAll(App.getContext());
    			Obra.setTecnica(tecnicas.get(position));
				break;
				
			case 2: 
								
				startActivity(new Intent(NavDrawerMainActivity.this, ExibirImagem.class));
				break;
			
			case 3: 
				
				break;	

			default:
				ArrayList<Artista> artistas = Artista.selectAll(App.getContext());
				Obra.setArtista(artistas.get(position));
				break;
			}           
            
    	}
    	
    	
    }   
        
}