package com.menu.sliding;

import com.menu.sliding.R;
import com.menu.sliding.GlobalLists;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
 
public class ModeFragment extends Fragment {
    
	Button auto;
	Button interval;
	Button hill;
	
    public ModeFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_mode, container, false);
        auto = (Button) rootView.findViewById(R.id.auto);
        
        //if already selected, disable button
		if(GlobalLists.getWorkout() == 1) {
			auto.setClickable(false);
			auto.setBackgroundColor(Color.parseColor("#6F7371"));
		}else {
			auto.setClickable(true);
			auto.setBackgroundColor(Color.parseColor("#FFFFFF"));
		}
		if(auto.isClickable()) {
			auto.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					if(GlobalLists.getWorkout() == 1) {
						auto.setEnabled(false);
					}
					GlobalLists.setWorkout(1);
					GlobalLists.setWorkoutString("Automatic");
					Fragment f = new HomeFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.frame_container, f);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			});
		}
		
		interval = (Button) rootView.findViewById(R.id.interval);
		if(GlobalLists.getWorkout() == 2) {
			interval.setClickable(false);
			interval.setBackgroundColor(Color.parseColor("#6F7371"));
		}else {
			interval.setClickable(true);
			interval.setBackgroundColor(Color.parseColor("#FFFFFF"));
		}
		if(interval.isClickable()) {
			interval.setOnClickListener(new View.OnClickListener(){
				
				@Override
				public void onClick(View v) {
					if(GlobalLists.getWorkout() == 2) {
						interval.setEnabled(false);
					}
					GlobalLists.setWorkout(2);
					GlobalLists.setWorkoutString("Interval");
					Fragment f = new HomeFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.frame_container, f);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			});
		}
		hill = (Button) rootView.findViewById(R.id.hillclimb);
		
		if(GlobalLists.getWorkout() == 3) {
			hill.setClickable(false);
			hill.setBackgroundColor(Color.parseColor("#6F7371"));
		}else {
			hill.setClickable(true);
			hill.setBackgroundColor(Color.parseColor("#FFFFFF"));
		}
		if(hill.isClickable()) {
			hill.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					if(GlobalLists.getWorkout() == 3) {
						hill.setEnabled(false);
					}
					GlobalLists.setWorkout(3);
					GlobalLists.setWorkoutString("Hill Climb");
					Fragment f = new HomeFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.frame_container, f);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			});
		}
        return rootView;
    }
    
}