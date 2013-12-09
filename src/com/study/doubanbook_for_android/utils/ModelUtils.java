package com.study.doubanbook_for_android.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.model.Book;
import com.study.doubanbook_for_android.model.BookItem;
import com.study.doubanbook_for_android.model.GeneralResult;

public class ModelUtils {
	private static String rootUrl = "https://api.douban.com";
	private static String bookSerchUrl = "/v2/book/search";

	public static String getSerchList(String about) {
		// ArrayList<Book> book = null;
		String result = "";
		try {
			result = NetUtils
					.downloadStr("https://api.douban.com/v2/book/search?q=book");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static GeneralResult<BookItem> getBookList(String s) {
		String result = getSerchList(s);
		Gson gson = new Gson();
		GeneralResult<BookItem> all = gson.fromJson(result,
				new TypeToken<GeneralResult<BookItem>>() {
				}.getType());
		if (all != null)
			return all;
		else
			return null;

	}

	public static void getSerchList() {
		try {
			String result = NetUtils
					.downloadStr("https://api.douban.com/v2/book/search?q=book&start=0&count=1");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getUrl() {
		return rootUrl + bookSerchUrl;
	}

}
