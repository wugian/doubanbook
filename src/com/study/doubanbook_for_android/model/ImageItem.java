package com.study.doubanbook_for_android.model;

/**
 * subclass of book item
 * 
 * @author tezuka-pc
 * 
 */
public class ImageItem {
	private String small;// "http:\/\/img1.douban.com\/spic\/s1001902.jpg",
	private String large;// "http:\/\/img1.douban.com\/lpic\/s1001902.jpg",
	private String medium;// "http:\/\/img1.douban.com\/mpic\/s1001902.jpg"

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

}
