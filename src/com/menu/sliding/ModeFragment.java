package com.menu.sliding;

import com.menu.sliding.R;
import com.menu.sliding.GlobalLists;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
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
        getFragmentManager().beginTransaction()
        // Add this transaction to the back stack
        .addToBackStack("found")
        .commit();
        auto = (Button) rootView.findViewById(R.id.auto);
		auto.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				GlobalLists.setWorkout(1);
				System.out.println (" popped: " + getActivity().getFragmentManager().popBackStackImmediate());
				/*Intent intent = new Intent(SelectModeActivity.this, com.hci.fitbeat.WorkoutActivity.class);
				intent.putExtra("workout", 1);
				startActivity(intent);*/
			}
		});
		
		interval = (Button) rootView.findViewById(R.id.interval);
		interval.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				GlobalLists.setWorkout(2);
				
				System.out.println (" popped: " + getFragmentManager().popBackStackImmediate());
				/*Intent intent = new Intent(SelectModeActivity.this, com.hci.fitbeat.WorkoutActivity.class);
				intent.putExtra("workout", 2);
				startActivity(intent);*/
			}
		});
		
		hill = (Button) rootView.findViewById(R.id.hillclimb);
		hill.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				GlobalLists.setWorkout(3);
				
				/*Intent intent = new Intent(SelectModeActivity.this, com.hci.fitbeat.WorkoutActivity.class);
				intent.putExtra("workout", 3);
				startActivity(intent);*/
			}
		});
	//	getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        return rootView;
    }
    
}