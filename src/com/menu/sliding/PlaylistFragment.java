package com.menu.sliding;

import java.util.ArrayList;
import java.util.HashMap;

import com.menu.sliding.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
 
public class PlaylistFragment extends ListFragment{
	// Songs list
	public ArrayList<HashMap<String,String>> songsList = new ArrayList<HashMap<String, String>>();
    public PlaylistFragment(){}
     
    @Override
    //public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //        Bundle savedInstanceState) {
    	
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
        //View rootView = inflater.inflate(R.layout.fragment_playlist, container, false);
          
        Bundle b = this.getArguments();
        if(b != null) {
        	songsList = (ArrayList<HashMap<String, String>>) b.get("playlist");
        }
		//songsList = (ArrayList<HashMap<String, String>>) b.get("playlist");
		ArrayList<HashMap<String, String>> songsListData = GlobalLists.getCur();
       /* ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();
        HashMap <String, String> bob = new HashMap<String, String >();
		bob.put("bob","bob");
		songsListData.add(bob);*/
		// looping through playlist
		for(int i = 0; i < songsList.size(); i++) {
			//creating new HashMap
			HashMap<String, String> song = songsList.get(i);
			System.out.println(" songsList: " + songsList.get(i));
			// adding HashList to ArrayList
			songsListData.add(song);
		}
		
		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(getActivity(), songsListData, 
						R.layout.playlist_item, new String[] {"songTitle"}, new int[] {R.id.songTitle});
		
		setListAdapter(adapter); // cursor for the listview
		
		// selecting single ListView item
		//ListView lv = (ListView) rootView.findViewById(R.id.list);
		// selecting single ListView item
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
				// getting listitem index
				int songIndex = position;
				
				GlobalLists.setIndex(songIndex);
				GlobalLists.setPlaylistFlag(1);
				Fragment f = new HomeFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.frame_container, f);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		
		});
       // return rootView;
    }
}