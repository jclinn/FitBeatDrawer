package com.menu.sliding;

import com.menu.sliding.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
 
public class IntensityFragment extends Fragment {
	Button easy;
	Button med;
	Button hard;
    public IntensityFragment(){}
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_intensity, container, false);
        easy = (Button) rootView.findViewById(R.id.easy);
        if(GlobalLists.getMode() == 1) {
			easy.setClickable(false);
			easy.setAlpha((float) 0.5);
		}else {
			easy.setClickable(true);
			easy.setAlpha((float) 1.0);
		}
		if(easy.isClickable()) {
			easy.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					GlobalLists.setMode(1);
					GlobalLists.setModeString("Easy");
					Fragment f = new HomeFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.frame_container, f);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			});
		}
		med = (Button) rootView.findViewById(R.id.medium);
		if(GlobalLists.getMode() == 2) {
			med.setClickable(false);
			med.setAlpha((float) 0.5);
		}else {
			med.setClickable(true);
			med.setAlpha((float) 1.0);
		}
		if(med.isClickable()) {
			med.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					GlobalLists.setMode(2);
					GlobalLists.setModeString("Medium");
					Fragment f = new HomeFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.frame_container, f);
					transaction.addToBackStack(null);
					transaction.commit();
				}
			});
		}
		hard = (Button) rootView.findViewById(R.id.hard);
		if(GlobalLists.getMode() == 3) {
			hard.setClickable(false);
			hard.setAlpha((float) 0.5);
		}else {
			hard.setClickable(true);
			hard.setAlpha((float) 1.0);
		}
		if(hard.isClickable()) {
			hard.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					GlobalLists.setMode(3);
					GlobalLists.setModeString("Hard");
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