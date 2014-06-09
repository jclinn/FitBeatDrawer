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

    	
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);

		ArrayList<HashMap<String, String>> songsListData = GlobalLists.getCur();
		// looping through playlist
		for(int i = 0; i < songsList.size(); i++) {
			//creating new HashMap
			HashMap<String, String> song = songsList.get(i);
			// adding HashList to ArrayList
			songsListData.add(song);
		}
		
		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(getActivity(), songsListData, 
						R.layout.playlist_item, new String[] {"songTitle"}, new int[] {R.id.songTitle});
		
		setListAdapter(adapter); // cursor for the listview

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
    }
}