package com.study.doubanbook_for_android.model;

import java.io.Serializable;

/**
 * subclass of annotations recommend the message of user
 * 
 * @author tezuka-pc
 * 
 */
public class AuthorUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4591431265383536507L;
	private String name;// ": "谁也认不出哥",
	private String is_banned;// ": false,
	private String is_suicide;// ": false,
	private String url;// ": "http://api.douban.com/v2/user/2727575",
	private String avatar;// ": "http://img3.douban.com/icon/u2727575-7.jpg",
	private String uid;// ": "hijetaime",
	private String alt;// home page of note to introduce qr_code or auto jump
						// ": "http://www.douban.com/people/hijetaime/",
	private String type;// ": "user",
	private String id;// ": "2727575",
	private String large_avatar;// ": "http://img3.douban.com/icon/up2727575-7.jpg"

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_banned() {
		return is_banned;
	}

	public void setIs_banned(String is_banned) {
		this.is_banned = is_banned;
	}

	public String getIs_suicide() {
		return is_suicide;
	}

	public void setIs_suicide(String is_suicide) {
		this.is_suicide = is_suicide;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLarge_avatar() {
		return large_avatar;
	}

	public void setLarge_avatar(String large_avatar) {
		this.large_avatar = large_avatar;
	}

}
