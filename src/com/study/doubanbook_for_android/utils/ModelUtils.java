package com.study.doubanbook_for_android.utils;

import java.net.URLEncoder;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.activity.BaseActivity;
import com.study.doubanbook_for_android.model.GeneralResult;

public class ModelUtils {
	private static String rootUrl = "https://api.douban.com";
	private static String bookSerchUrl = "/v2/book/search";

	public static String getSerchList(String searchContent, int start) {
		String result = "";
		try {
			// TODO 将中文GBK编码转化成UTF-8编成,否则产生URL乱码
			String cur = URLEncoder.encode(searchContent, "UTF-8");

			result = NetUtils.downloadStr(rootUrl + bookSerchUrl + "?" + "q="
					+ cur + "&start=" + (start + 1) + "&count="
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

}
