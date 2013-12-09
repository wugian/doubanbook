package com.study.doubanbook_for_android.model;

/**
 * subclass of bookItem
 * 
 * @author tezuka-pc
 * 
 */
public class Rating {
	private int max;
	private int numRaters;
	private double average;
	private int min;

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getNumRaters() {
		return numRaters;
	}

	public void setNumRaters(int numRaters) {
		this.numRaters = numRaters;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	// {"max":10,"numRaters":9438,"average":"9.1","min":0}
}
