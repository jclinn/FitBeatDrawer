package com.menu.sliding;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Track;
import com.menu.sliding.Utilities;

import java.io.FilenameFilter;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class HomeFragment extends Fragment 
implements OnCompletionListener, SeekBar.OnSeekBarChangeListener, SensorEventListener {
	private ImageButton btnPlay;
    private ImageButton btnForward;
    private ImageButton btnBackward;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
   // private ImageButton btnPlaylist;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    // Media Player
    private  MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();;
    //private SongsManager songManager;
    private Utilities utils;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private int currentSongIndex = 0;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> masterList = new HashMap<String, String>();
    private ArrayList<HashMap<String, String>> songsListEasy = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListMed = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListHard = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListEasy1 = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListMed1 = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListHard1 = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListEasy2 = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListMed2 = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListHard2 = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> songsListCur;
    private int genListSize;
    private int easyListSize;
    private int medListSize;
    private int hardListSize;
    protected boolean uploaded = false;
    protected String easy1 = "easy1";
    protected String med1 = "med1";
    protected String hard1 = "hard1";
    protected String easy2 = "easy2";
    protected String med2 = "med2";
    protected String hard2 = "hard2";    
    private int mode = 0;
    private int workout = 1;
    private int comboMode = 0;
    private boolean hill = false;
    
    
    
    //songmanager variables
    final String MEDIA_PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/";
	final String DB = Environment.getExternalStorageDirectory()
            .getPath() + "/songlist.txt";
	//private ArrayList<HashMap<String,String>> songsList = new ArrayList<HashMap<String, String>>();
	private ArrayList<HashMap<String,String>> songsListTempo = new ArrayList<HashMap<String, String>>();
	private boolean completedTask = false;
 
    //sensor variables
	Sensor accelerometer;
	SensorManager sm;
	TextView acceleration;   
	public HomeFragment(){}
	     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	  
	        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
	        mode = GlobalLists.getMode();
	        workout = GlobalLists.getWorkout();
	        
	     // All player buttons
	        btnPlay = (ImageButton) rootView.findViewById(R.id.btnPlay);
	        btnForward = (ImageButton) rootView.findViewById(R.id.btnForward);
	        btnBackward = (ImageButton) rootView.findViewById(R.id.btnBackward);
	        btnNext = (ImageButton) rootView.findViewById(R.id.btnNext);
	        btnPrevious = (ImageButton) rootView.findViewById(R.id.btnPrevious);
	       // btnPlaylist = (ImageButton) rootView.findViewById(R.id.btnPlaylist);
	        btnRepeat = (ImageButton) rootView.findViewById(R.id.btnRepeat);
	        btnShuffle = (ImageButton) rootView.findViewById(R.id.btnShuffle);
	        songProgressBar = (SeekBar) rootView.findViewById(R.id.songProgressBar);
	        songTitleLabel = (TextView) rootView.findViewById(R.id.songTitle);
	        songCurrentDurationLabel = (TextView) rootView.findViewById(R.id.songCurrentDurationLabel);
	        songTotalDurationLabel = (TextView) rootView.findViewById(R.id.songTotalDurationLabel);
	        
	     // Mediaplayer
	        //mp = new MediaPlayer();
	       mp = GlobalLists.getMP();
	        // songManager = new SongsManager();
	        utils = new Utilities();
	        
	        //Listeners
	        songProgressBar.setOnSeekBarChangeListener(this);
	        mp.setOnCompletionListener(this);
	        
	        new ParseInitialize().execute("");
	       // Parse.initialize(getActivity(), "1HNsMPWDxmDo3SE6zwtsTqJMw8M63Ajw9yHUb88e", "vQ0lbV85eGTgp3d6PJ3rM82AuhsfpLqIsdKEstyy");
	        try {
	        	new Connection().execute("");
				songsList = getPlayList();
				genListSize = songsList.size();
				easyListSize = songsListEasy1.size();
				medListSize = songsListMed1.size();
				hardListSize = songsListHard1.size();
				System.out.println("masterlistsize: " + masterList.size() + " easy: " + songsListEasy1.size() +
						" med: " + songsListMed1.size() + " hard: " + songsListHard1.size());
			} catch (EchoNestException e) {
				e.printStackTrace();
			}
	        
	        /*getFragmentManager().beginTransaction()
            // Add this transaction to the back stack
            .addToBackStack("found")
            .commit();*/
	        
	      //update playlists
			comboMode = checkModeIntensity(GlobalLists.getMode(), GlobalLists.getWorkout());
	       if(genListSize > 0) {
		        //By default play first song
		        playSong(0, 0);
		        /**
		         * Play button click event
		         * plays a song and changes button to pause image
		         * pauses a song and changes button to play image
		         * */
		        btnPlay.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                // check for already playing
		                if(mp.isPlaying()){
		                    if(mp!=null){
		                        mp.pause();
		                        // Changing button image to play button
		                        btnPlay.setImageResource(R.drawable.btn_play);
		                    }
		                }else{
		                    // Resume song
		                    if(mp!=null){
		                        mp.start();
		                        // Changing button image to pause button
		                        btnPlay.setImageResource(R.drawable.btn_pause);
		                    }
		                }
		 
		            }
		        });
		        
		        /*
		         *  Button Click event for Playlist click event
		         *  Launches list activity which displays list of songs
		         */
		      /*  btnPlaylist.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent i = new Intent(getActivity().getApplicationContext(), PlaylistFragment.class);
						i.putExtra("playlist", songsListCur);
						startActivityForResult(i, 100);
						
					}
				});*/
		        
		        /**
		         * Forward button click event
		         * Forwards song specified seconds
		         * */
		        btnForward.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                // get current song position
		                int currentPosition = mp.getCurrentPosition();
		                // check if seekForward time is lesser than song duration
		                if(currentPosition + seekForwardTime <= mp.getDuration()){
		                    // forward song
		                    mp.seekTo(currentPosition + seekForwardTime);
		                }else{
		                    // forward to end position
		                    mp.seekTo(mp.getDuration());
		                }
		            }
		        });
		        
		        /**
		         * Backward button click event
		         * Backward song to specified seconds
		         * */
		        btnBackward.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                // get current song position
		                int currentPosition = mp.getCurrentPosition();
		                // check if seekBackward time is greater than 0 sec
		                if(currentPosition - seekBackwardTime >= 0){
		                    // forward song
		                    mp.seekTo(currentPosition - seekBackwardTime);
		                }else{
		                    // backward to starting position
		                    mp.seekTo(0);
		                }
		 
		            }
		        });
		        
		        /**
		         * Next button click event
		         * Plays next song by taking currentSongIndex + 1
		         * */
		        btnNext.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                // check if next song is there or not
		                if(GlobalLists.getIndex() < (songsListCur.size() - 1)){
		                    playSong(GlobalLists.getIndex() + 1, mode);
		                    
		                   // currentSongIndex = currentSongIndex + 1;
		                    GlobalLists.setIndex(GlobalLists.getIndex() +1);
		                }else{
		                    // play first song
		                    playSong(0, mode);
		                    //currentSongIndex = 0;
		                    GlobalLists.setIndex(0);
		                }
		 
		            }
		        });
		        
		        /**
		         * Back button click event
		         * Plays previous song by currentSongIndex - 1
		         * */
		        btnPrevious.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                if(GlobalLists.getIndex() > 0){
		                    playSong(GlobalLists.getIndex() - 1, mode);
		                    GlobalLists.setIndex(GlobalLists.getIndex() -1);// = currentSongIndex - 1;
		                }else{
		                    // play last song
		                    playSong(songsListCur.size() - 1, mode);
		                    //currentSongIndex = songsListCur.size() - 1;
		                    GlobalLists.setIndex(songsListCur.size() - 1);
		                }
		 
		            }
		        });
		        
		        /**
		         * Button Click event for Repeat button
		         * Enables repeat flag to true
		         * */
		        btnRepeat.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                if(isRepeat){
		                    isRepeat = false;
		                    //Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
		                    btnRepeat.setImageResource(R.drawable.btn_repeat);
		                }else{
		                    // make repeat to true
		                    isRepeat = true;
		                   // Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
		                    // make shuffle to false
		                    isShuffle = false;
		                    btnRepeat.setImageResource(R.drawable.btn_repeat); //update to focused later
		                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
		                }
		            }
		        });
		        
		        /**
		         * Button Click event for Shuffle button
		         * Enables shuffle flag to true
		         * */
		        btnShuffle.setOnClickListener(new View.OnClickListener() {
		 
		            @Override
		            public void onClick(View arg0) {
		                if(isShuffle){
		                    isShuffle = false;
		                    //Toast.makeText(rootView.getContext()getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
		                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
		                }else{
		                    // make repeat to true
		                    isShuffle= true;
		                    //Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
		                    // make shuffle to false
		                    isRepeat = false;
		                    btnShuffle.setImageResource(R.drawable.btn_shuffle); //_focused);
		                    btnRepeat.setImageResource(R.drawable.btn_repeat);
		                }
		            }
		        });
	        
	       }
	       else {
	    	   songTitleLabel.setText("No Songs Found");
	       }
	        
	        return rootView;
	    }
	    
	    
	    private class ParseInitialize extends AsyncTask {

			@Override
			protected Object doInBackground(Object... arg0) {
				Parse.initialize(getActivity(), "1HNsMPWDxmDo3SE6zwtsTqJMw8M63Ajw9yHUb88e", "vQ0lbV85eGTgp3d6PJ3rM82AuhsfpLqIsdKEstyy");
				return null;
			}
	    	
	    }
	    
	    /* 
	     * Recieving song index from playlist view and play the song
	     */
	    @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	super.onActivityResult(requestCode, resultCode, data);
	    	if(resultCode == 100) {
	    		currentSongIndex = data.getExtras().getInt("songIndex");
	    		//play selected song
	    		playSong(currentSongIndex, mode);
	    	}
	    }
	    /*
	     * Function to play a song
	     * @param songIndex - index of song
	     */
	    public void playSong(int songIndex, int playlist) {
	    	//Play song
	    	try {
	    		//checkModeIntensity(workout, mode);' mPlayer.release();
	    		//mp.release();
	    		System.out.println("mode = " + mode + " size = " + songsListCur.size() + "index: " + songIndex + " song: ");// + songsListCur.get(songIndex));
	    		mp.reset();
	    		mp.setDataSource(songsListCur.get(songIndex).get("songPath"));
	    		mp.prepare();
	    		mp.start();
	    		//Displaying Song Title
	    		String songTitle = songsListCur.get(songIndex).get("songTitle");
	    		songTitleLabel.setText(songTitle);
	    		
	    		//Changing Button Image to pause image
	    		btnPlay.setImageResource(R.drawable.btn_pause);
	    		
	    		// set Progress bar values
	    		songProgressBar.setProgress(0);
	    		songProgressBar.setMax(100);
	    		
	    		//Updating progress bar
	    		updateProgressBar();
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSensorChanged(SensorEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		/**
	     * Update timer on seekbar
	     * */
	    public void updateProgressBar() {
	        mHandler.postDelayed(mUpdateTimeTask, 100);
	    }   
	 
	    /**
	     * Background Runnable thread
	     * */
	    private Runnable mUpdateTimeTask = new Runnable() {
	           public void run() {
	               long totalDuration = mp.getDuration();
	               long currentDuration = mp.getCurrentPosition();
	 
	               // Displaying Total Duration time
	               songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
	               // Displaying time completed playing
	               songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));
	 
	               // Updating progress bar
	               int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
	               //Log.d("Progress", ""+progress);
	               songProgressBar.setProgress(progress);
	 
	               // Running this thread after 100 milliseconds
	               mHandler.postDelayed(this, 100);
	           }
	        };
	 
	    /**
	     *
	     * */
	    @Override
	    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
	 
	    }
	 
	    /**
	     * When user starts moving the progress handler
	     * */
	    @Override
	    public void onStartTrackingTouch(SeekBar seekBar) {
	        // remove message Handler from updating progress bar
	        mHandler.removeCallbacks(mUpdateTimeTask);
	    }
	 
	    /**
	     * When user stops moving the progress hanlder
	     * */
	    @Override
	    public void onStopTrackingTouch(SeekBar seekBar) {
	        mHandler.removeCallbacks(mUpdateTimeTask);
	        int totalDuration = mp.getDuration();
	        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);
	 
	        // forward or backward to certain seconds
	        mp.seekTo(currentPosition);
	 
	        // update timer progress again
	        updateProgressBar();
	    }

		/**
	     * On Song Playing completed
	     * if repeat is ON play same song again
	     * if shuffle is ON play random song
	     * */
	    @Override
	    public void onCompletion(MediaPlayer arg0) {
	    	
	        // check for repeat is ON or OFF
	        if(isRepeat){
	            // repeat is on play same song again
	            playSong(GlobalLists.getIndex(), mode);
	        } else if(isShuffle){
	            // shuffle is on - play a random song
	            Random rand = new Random();
	            //currentSongIndex = rand.nextInt((songsListCur.size() - 1) - 0 + 1) + 0;
	            GlobalLists.setIndex(rand.nextInt((songsListCur.size() - 1) - 0 + 1) + 0);
	            playSong(GlobalLists.getIndex(), mode);
	        } else{
	            // no repeat or shuffle ON - play next song
	            if(GlobalLists.getIndex() < (songsListCur.size() - 1)){
	                playSong(GlobalLists.getIndex() + 1, mode);
	                //currentSongIndex = currentSongIndex + 1;
	                GlobalLists.setIndex(GlobalLists.getIndex() + 1);
	            }else{
	                // play first song
	                playSong(0, mode);
	                //currentSongIndex = 0;
	                GlobalLists.setIndex(0);
	            }
	        }
	    }
		
		/*
		 * Function to read all mp3 files from sdcard 
		 * and store the details in ArrayList
		 */
		public ArrayList<HashMap<String, String>> getPlayList() throws EchoNestException {
			while(!completedTask) {
				System.out.println("waiting on thread");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(" size auto interval " + GlobalLists.getEasy1().size() + " easy: " + GlobalLists.getEasy().size() + " " + 
					GlobalLists.getMed1().size() +  " med: " + GlobalLists.getMed().size() +  " hard: " + GlobalLists.getHard().size() + " " + GlobalLists.getHard1().size());
			
			if( GlobalLists.getlistFlag() == 0) {
				GlobalLists.setEasy(songsListEasy);
				GlobalLists.setMed(songsListMed);
				GlobalLists.setHard(songsListHard);
				GlobalLists.setEasy1(songsListEasy1);
				GlobalLists.setEasy2(songsListEasy2);
				GlobalLists.setMed1(songsListMed1);
				GlobalLists.setMed2(songsListMed2);
				GlobalLists.setHard1(songsListHard);
				GlobalLists.setHard2(songsListHard2);	
			
			System.out.println(" size auto interval " + GlobalLists.getEasy1().size() + 
					GlobalLists.getMed1().size() + GlobalLists.getHard1().size());
			
			}
			System.out.println(" sizes interval " + GlobalLists.getEasy1().size() + " easy: " + GlobalLists.getEasy().size() + " " + 
					GlobalLists.getMed1().size() +  " med: " + GlobalLists.getMed().size() +  " hard: " + GlobalLists.getHard().size() + " " + GlobalLists.getHard1().size());
			GlobalLists.setlistFlag(1);
			GlobalLists.setEasyInterval(updateIntervalList(GlobalLists.getEasy1(), GlobalLists.getMed1()));
			GlobalLists.setMedInterval(updateIntervalList(GlobalLists.getEasy2(),  GlobalLists.getMed2()));
			GlobalLists.setHardInterval(updateIntervalList( GlobalLists.getMed2(),  GlobalLists.getHard2()));
			GlobalLists.setEasyHill(updateHillClimbList(GlobalLists.getEasy1(), GlobalLists.getEasy2(), GlobalLists.getMed1(), GlobalLists.getMed2()));
			GlobalLists.setMedHill(updateHillClimbList(GlobalLists.getEasy2(),  GlobalLists.getMed1(),  GlobalLists.getMed2(), GlobalLists.getHard1()));
			GlobalLists.setHardHill(updateHillClimbList( GlobalLists.getMed1(),  GlobalLists.getMed2(), GlobalLists.getHard1(), GlobalLists.getHard2()));
			System.out.println(" size interval " + GlobalLists.getEasyInterval().size() + 
					GlobalLists.getMedInterval().size() + GlobalLists.getHardInterval().size());
			File home = new File(MEDIA_PATH);
			if(home.listFiles(new FileExtensionFilter()) != null) {
				System.out.println("found files");
				if (home.listFiles(new FileExtensionFilter()).length > 0) {
					for( File file: home.listFiles(new FileExtensionFilter())) {
						HashMap<String, String> song = new HashMap<String, String>();
						song.put("songTitle", file.getName().substring(0, (file.getName().length()-4))); //-4 because .mp3
						song.put("songPath",  file.getPath());
						
						songsList.add(song);
					}
				}
			}
			System.out.println("size of songslist after SongsManager: " + songsList.size());
			return songsList;
		}
		
		int count = 0;
		private class Connection extends AsyncTask {

			@Override
			protected Object doInBackground(Object... params) {
				if(GlobalLists.getFirst()==1) {
					completedTask = true;
					return null;
				}
				GlobalLists.setFirst(1);
				EchoNestAPI en = new EchoNestAPI("YONAIFTTA0HFKM9J4" );
				File home = new File(MEDIA_PATH);
				//File dbSongList = new File(DB);

					if(home.listFiles(new FileExtensionFilter()) != null) {
						
						if (home.listFiles(new FileExtensionFilter()).length > 0) {
							for( File file: home.listFiles(new FileExtensionFilter())) {
								try {
									
									 File currFile = new File(file.getPath());
									 if(!currFile.exists()) {
										 System.err.println("Can't find in async " + file.getPath());
									 }
									 else {
										 uploaded = false;
										String title = file.getName().substring(0, (file.getName().length()-4));
										String path = file.getPath();
										// System.out.println("file name" + file.getName().substring(0, (file.getName().length()-4)) + " counter" + count);
										 ParseQuery<ParseObject> query = ParseQuery.getQuery("SongObject");
										 query.whereEqualTo("title", title );
									       	try {
									            List<ParseObject> queryResult = query.find();
									            for(ParseObject so : queryResult) {
									                ParseObject songObj = new ParseObject("SongObject");
									                System.out.println("inside parse " + so.getString("title"));
									                uploaded = true;
									                
													masterList.put(title, path);
													 
													// adds mode
													String mode = so.getString("mode");
													HashMap<String, String>song = new HashMap<String, String>();
													song.put("songTitle", title); //-4 because .mp3
													song.put("songPath", file.getPath());
													//System.out.println("mode: " + mode);
													 
													if(mode.equals(easy1)) {
														song.put("mode", easy1);
														songsListEasy1.add(song);
														songsListEasy.add(song);
													} else if(mode.equals(easy2)) {
														song.put("mode", easy2);
														songsListEasy2.add(song);
														songsListEasy.add(song);
													} else if(mode.equals(med1)) {
														song.put("mode", med1);
														songsListMed1.add(song);
														songsListMed.add(song);
														System.out.println("med song added");
													} else if(mode.equals(med2)) {
														song.put("mode", med2);
														songsListMed2.add(song);
														songsListMed.add(song);
														System.out.println("med song added");
													} else if(mode.equals(hard1)) {
													    song.put("mode", hard1);
													    songsListHard1.add(song);
													    songsListHard.add(song);
													} else {
														song.put("mode", hard2);
													    songsListHard2.add(song);
													    songsListHard.add(song);
													}
													
													
													
									            }
									        }
									        catch(ParseException e) {
									            Log.d("mNameList", "Error: " + e.getMessage());
									        }

										 
										 if(!uploaded) {
											// uploaded = false;
										    System.out.println("EchoNest API call");
							                Track track = en.uploadTrack(currFile, true);
							                track.waitForAnalysis(30000);
							                //System.out.println("waiting for analysis " + file.getPath() + " counter " + count + "what?");
							                count++;
							                if (track.getStatus() == Track.AnalysisStatus.COMPLETE) {
							                    System.out.println("Tempo: " + track.getTempo());
												HashMap<String, String> song = new HashMap<String, String>();
												song.put("songPath",  file.getPath());
												
												int tempo = (int) track.getTempo();
												String mode = "";
												if(tempo >= 130 && tempo < 145 ) { //hard 1
													song.put("mode", hard1);
													songsListHard1.add(song);
													songsListHard.add(song);
													mode = "hard1";
												}
												else if( tempo > 145) { // hard 2
													song.put("mode", hard2);
													songsListHard2.add(song);
													songsListHard.add(song);
													mode = "hard2";
												}
												else if(tempo >= 115 && tempo < 130) { // med 2
													song.put("mode", med2);
													songsListMed2.add(song);
													songsListMed.add(song);
													mode = "med2";
												}
												else if( tempo >= 90 && tempo < 115) { // med 1
													song.put("mode", med1);
													songsListMed1.add(song);
													songsListMed.add(song);
													mode = "med1";
												}
												else if( tempo >= 85 && tempo < 90) { //easy 2
													song.put("mode", easy2);
													songsListEasy2.add(song);
													songsListEasy.add(song);
													mode = "easy2";
												}
												else { // easy 
													song.put("mode", easy1);
												    songsListEasy1.add(song);
												    songsListEasy.add(song);
													mode ="easy1";
												}
												
											
												
										        ParseObject songObject = new ParseObject("SongObject");
										        songObject.put("path", file.getPath());
										        songObject.put("title", title);
										        songObject.put("mode", mode);
										        songObject.put("tempo", tempo);
										        songObject.saveInBackground();
										        
										        masterList.put(title, path);
							                } else {
							                    System.err.println("Trouble analysing track " + track.getStatus());
							                }
										 }
									  }
									 
									 
					            } catch (IOException e) {
					                System.err.println("Trouble uploading file");
					            } catch (EchoNestException e) {
									e.printStackTrace();
								}
							}
						}
					}
					

			//	System.out.println("home found: " + home + " files: " + Environment.getExternalStorageDirectory().listFiles());

				completedTask = true;
				return null;
			}
			
		}
				
		
		/*
		 * Class to filter files which have .mp3 extension 
		 */
		
		private class FileExtensionFilter implements FilenameFilter {

			@Override
			public boolean accept(File dir, String name) { //dir= where file was found; name
				return (name.endsWith(".mp3") || name.endsWith(".MP3"));
			}
			
		}
		
		
		
		// returns mode and intensity combo from user selection
		public int checkModeIntensity(int mode, int workout) {
			System.out.println("updating songsListCur");
			int comboMode;
			//auto mode
			if(workout == 1) {
				if( mode == 1 ) { //easy
					comboMode = 1;
					songsListCur = GlobalLists.getEasy();//songsListEasy;
				} else if (mode == 2) { //medium
					comboMode = 2;
					songsListCur = GlobalLists.getMed();//songsListMed;
				} else { //hard
					comboMode = 3;
					songsListCur = GlobalLists.getHard();//songsListHard;
				}
			} else if (workout == 2) { //interval
				if( mode == 1 ) { //easy
					songsListCur = GlobalLists.getEasyInterval();
					comboMode = 4;
				} else if (mode == 2) { //medium
					songsListCur = GlobalLists.getMedInterval();
					comboMode = 5;
				} else { //hard
					songsListCur = GlobalLists.getHardInterval();
					comboMode = 6;
				}
			} else  { // hillclimb
				if( mode == 1 ) { //easy
					songsListCur = GlobalLists.getEasyHill();
					comboMode = 7;
				} else if (mode == 2) { //medium
					songsListCur = GlobalLists.getMedHill();
					comboMode = 8;
				} else { //hard
					songsListCur = GlobalLists.getHardHill();
					comboMode = 9;
				}
				
				
			}
			GlobalLists.setCur(songsListCur);
			return comboMode;
		}
		
		public ArrayList<HashMap<String, String>> updateIntervalList ( ArrayList<HashMap<String, String>> slow, ArrayList<HashMap<String, String>> fast) {
			ArrayList<HashMap<String, String>> intervalList = new ArrayList<HashMap<String, String>>();
			int slowCount = 0;
			int fastCount = 0;
			boolean empty = false;
			//System.out.println(" slow size: " + slow.size());
			//System.out.println("fast size: " + fast.size());
			while(empty==false) {
				//System.out.println(" slowCount " + slowCount + " fastCount " + fastCount);
				if(slowCount <= slow.size() -1) {
					//System.out.println("in slow");
					intervalList.add(slow.get(slowCount));
					slowCount++;
				}
				else if(fastCount<= fast.size()-1){
					//System.out.println("in fast");
					intervalList.add(fast.get(fastCount));
					fastCount++;
				} else {
					empty = true;
				}
			}
			//System.out.println("intervalList size " + intervalList.size());
			return intervalList;
		}
		
		
		public ArrayList<HashMap<String, String>> updateHillClimbList ( ArrayList<HashMap<String, String>> slow, 
				ArrayList<HashMap<String, String>> med, ArrayList<HashMap<String, String>> avg, ArrayList<HashMap<String, String>> fast) {
			ArrayList<HashMap<String, String>> intervalList = new ArrayList<HashMap<String, String>>();
			Random rand = new Random();
	        int slowIndex = 0;
	        int medIndex = 0;
	        int avgIndex = 0;
	        int fastIndex = 0;

			slowIndex = rand.nextInt((slow.size() - 1) - 0 + 1) + 0;
			intervalList.add(slow.get(slowIndex));
			medIndex = rand.nextInt((med.size() - 1) - 0 + 1) + 0;
			intervalList.add(med.get(medIndex));
			avgIndex = rand.nextInt((avg.size() - 1) - 0 + 1) + 0;
			intervalList.add(avg.get(avgIndex));
			fastIndex = rand.nextInt((fast.size() - 1) - 0 + 1) + 0;
			intervalList.add(fast.get(fastIndex));
			avgIndex = rand.nextInt((avg.size() - 1) - 0 + 1) + 0;
			intervalList.add(avg.get(avgIndex));
			medIndex = rand.nextInt((med.size() - 1) - 0 + 1) + 0;
			intervalList.add(med.get(medIndex));
			slowIndex = rand.nextInt((slow.size() - 1) - 0 + 1) + 0;
			intervalList.add(slow.get(slowIndex));
			return intervalList;
		}
}
