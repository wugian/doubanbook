package com.study.doubanbook_for_android.model;

public class AcToken {
	private String access_token;// ":"a14afef0f66fcffce3e0fcd2e34f6ff4",
	private int expires_in;// ":3920,
	private String refresh_token;// ":"5d633d136b6d56a41829b73a424803ec",
	private int douban_user_id;// ":"1221"

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getDouban_user_id() {
		return douban_user_id;
	}

	public void setDouban_user_id(int douban_user_id) {
		this.douban_user_id = douban_user_id;
	}

}
