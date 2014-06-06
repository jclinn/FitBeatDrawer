package com.menu.sliding;

import java.util.ArrayList;
import java.util.HashMap;

import android.media.MediaPlayer;

public class GlobalLists {
	private static int mode = 0;
	private static int workout =1;
	private static int comboMode = 0;
	private static int first = 0;
	private static int listFlag = 0;
	private static int index = 0;
	
	private static  MediaPlayer mp = new MediaPlayer();
	private static HashMap<String, String> masterList = new HashMap<String, String>();
    private static ArrayList<HashMap<String, String>> songsListEasy = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListMed = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListHard = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListEasy1 = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListMed1 = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListHard1 = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListEasy2 = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListMed2 = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListHard2 = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListEasyHill = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListMedHill = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListHardHill = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListEasyInterval = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListMedInterval = new ArrayList<HashMap<String, String>>();
    private static ArrayList<HashMap<String, String>> songsListHardInterval = new ArrayList<HashMap<String, String>>();
    
    
    private static ArrayList<HashMap<String, String>> songsListCur;
    
    public static ArrayList<HashMap<String, String>> getCur () {
    	return songsListCur;
    }
    
    public static void setCur( ArrayList<HashMap<String, String>> cur) {
    	songsListCur = cur;
    }
    
    public static void setMode(int mode2) {
    	mode = mode2;
    }
    
    public static void setWorkout(int workout2) {
    	workout= workout2;
    }
    
    public static int getMode() {
    	return mode;
    }
    
    public static int getWorkout() {
    	return workout;
    }
    
    public static void setCombo(int combo2) {
    	comboMode= combo2;
    }
    
    public static int getCombo() {
    	return comboMode;
    }
    
    public static ArrayList<HashMap<String, String>> getEasy1 () {
    	return songsListEasy1;
    }
    
    public static void setEasy1( ArrayList<HashMap<String, String>> cur) {
    	songsListEasy1 = cur;
    }
    public static ArrayList<HashMap<String, String>> getEasy2 () {
    	return songsListEasy2;
    }
    
    public static void setEasy2( ArrayList<HashMap<String, String>> cur) {
    	songsListEasy2 = cur;
    }
    public static ArrayList<HashMap<String, String>> getMed1 () {
    	return songsListMed1;
    }
    
    public static void setMed1( ArrayList<HashMap<String, String>> cur) {
    	songsListMed1 = cur;
    }
    public static ArrayList<HashMap<String, String>> getMed2 () {
    	return songsListMed2;
    }
    
    public static void setMed2( ArrayList<HashMap<String, String>> cur) {
    	songsListMed2 = cur;
    }
    
    public static ArrayList<HashMap<String, String>> getHard1 () {
    	return songsListHard1;
    }
    public static void setHard1( ArrayList<HashMap<String, String>> cur) {
    	songsListHard1 = cur;
    }
    
    public static ArrayList<HashMap<String, String>> getHard2 () {
    	return songsListHard2;
    }
    public static void setHard2( ArrayList<HashMap<String, String>> cur) {
    	songsListHard2 = cur;
    }
    public static ArrayList<HashMap<String, String>> getEasyHill() {
    	return songsListEasyHill;
    }
    public static void setEasyHill( ArrayList<HashMap<String, String>> cur) {
    	songsListEasyHill= cur;
    }
    public static ArrayList<HashMap<String, String>> getMedHill() {
    	return songsListMedHill;
    }
    public static void setMedHill( ArrayList<HashMap<String, String>> cur) {
    	songsListMedHill= cur;
    }
    public static ArrayList<HashMap<String, String>> getHardHill() {
    	return songsListHardHill;
    }
    public static void setHardHill( ArrayList<HashMap<String, String>> cur) {
    	songsListHardHill= cur;
    }
    
    public static ArrayList<HashMap<String, String>> getEasyInterval() {
    	return songsListEasyInterval;
    }
    public static void setEasyInterval( ArrayList<HashMap<String, String>> cur) {
    	songsListEasyInterval= cur;
    }
    public static ArrayList<HashMap<String, String>> getMedInterval() {
    	return songsListMedInterval;
    }
    public static void setMedInterval( ArrayList<HashMap<String, String>> cur) {
    	songsListMedInterval= cur;
    }
    public static ArrayList<HashMap<String, String>> getHardInterval() {
    	return songsListHardInterval;
    }
    public static void setHardInterval( ArrayList<HashMap<String, String>> cur) {
    	songsListHardInterval= cur;
    }
    public static ArrayList<HashMap<String, String>> getHard() {
    	return songsListHard;
    }
    public static void setHard( ArrayList<HashMap<String, String>> cur) {
    	songsListHard= cur;
    }
    public static ArrayList<HashMap<String, String>> getMed() {
    	return songsListMed;
    }
    public static void setMed( ArrayList<HashMap<String, String>> cur) {
    	songsListMed= cur;
    }
    public static ArrayList<HashMap<String, String>> getEasy() {
    	return songsListEasy;
    }
    public static void setEasy( ArrayList<HashMap<String, String>> cur) {
    	songsListEasy= cur;
    }
    
    public static void setFirst( int f) {
    	first = f;
    }
    public static int getFirst( ) {
    	return first;
    }
    public static void setlistFlag( int f) {
    	listFlag = f;
    }
    public static int getlistFlag( ) {
    	return listFlag;
    }
    public static void setMP(MediaPlayer m) { mp = m;}
    public static MediaPlayer getMP() { return mp;}
    public static void setIndex(int i) { index = i; }
    public static int getIndex(){ return index;} 
}
