package com.menu.sliding;

import com.menu.sliding.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
 
public class IntensityFragment extends Fragment {
	Button easy;
	Button med;
	Button hard;
	private int workout; 
    public IntensityFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_intensity, container, false);
        easy = (Button) rootView.findViewById(R.id.easy);
		easy.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				GlobalLists.setMode(1);
				Fragment f = new HomeFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.frame_container, f);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		
		med = (Button) rootView.findViewById(R.id.medium);
		med.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				GlobalLists.setMode(2);
				Fragment f = new HomeFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.frame_container, f);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		
		hard = (Button) rootView.findViewById(R.id.hard);
		hard.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				GlobalLists.setMode(3);
				Fragment f = new HomeFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.frame_container, f);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});  
        return rootView;
    }
}