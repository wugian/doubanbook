package com.study.doubanbook_for_android.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.activity.BaseActivity;
import com.study.doubanbook_for_android.model.GeneralResult;

public class ModelUtils {
	private static String rootUrl = "https://api.douban.com";
	private static String bookSerchUrl = "/v2/book/search";

	// private static String getAddParm(String baseUrl, List<String> key,
	// List<String> value) {
	// return null;
	// }

	public static String getSerchList(String about, int start) {
		String result = "";
		try {
			result = NetUtils
					.downloadStr("https://api.douban.com/v2/book/search?q="
							+ about + "&start=" + start + "&count="
							+ BaseActivity.PAGE_COUNT);
			Log.i("NET", "request result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static GeneralResult getBookList(String s, int start) {
		String result = getSerchList(s, start);
		Gson gson = new Gson();
		GeneralResult all = gson.fromJson(result,
				new TypeToken<GeneralResult>() {
				}.getType());
		if (all != null)
			return all;
		else
			return null;

	}

	private static String getUrl() {
		return rootUrl + bookSerchUrl;
	}

}
