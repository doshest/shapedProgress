package com.dosh.androidshapeprogressbar.utils;

import java.util.ArrayList;
import java.util.List;

import android.R.color;

public class ColorUtil {
	private static final List<Integer> colorArray = new ArrayList<Integer>();
	
	public static List<Integer> getColorArray(){
		colorArray.add(color.holo_blue_bright);
		colorArray.add(color.holo_blue_dark);
		colorArray.add(color.holo_blue_light);
		colorArray.add(color.holo_green_dark);
		colorArray.add(color.holo_green_light);
		colorArray.add(color.holo_orange_dark);
		colorArray.add(color.holo_orange_light);
		colorArray.add(color.holo_purple);
		colorArray.add(color.holo_red_dark);
		colorArray.add(color.holo_red_light);
		return colorArray;
	}
}
