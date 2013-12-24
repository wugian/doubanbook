package com.study.doubanbook_for_android.model;

public class URLMananeger {
	// root url
	public static final String ROOT_ULR = "https://api.douban.com";
	// book writer search url
	public static final String BOOK_WRITER_SEARCHR_URL = "/v2/book/search";
	// book note url
	public static final String BOOK_NOTE_LIST_URL = "/v2/book/:id/annotations";
	//collect book url
	public static final String BOOK_COLLECT_URL = "/v2/book/:id/collection";
	// get the user's collection url
	public static final String USER_COLLECTION_URL = "/v2/book/user/:name/collections";
	//user search url
	public static final String USER_SEARCH_URL = "/v2/user";


}
