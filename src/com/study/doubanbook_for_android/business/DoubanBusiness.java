package com.study.doubanbook_for_android.business;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.model.URLMananeger;
import com.study.doubanbook_for_android.utils.NetUtils;

public class DoubanBusiness {

	public GeneralResult getSearchList(String q, int start, int count) {
		List<String> keys = new ArrayList<String>();
		keys.add("q");
		keys.add("start");
		keys.add("count");
		List<String> values = new ArrayList<String>();
		try {
			values.add(URLEncoder.encode(q, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// values.add(q);
		values.add(start + "");
		values.add(count + "");
		String s = "";
		try {
			s = NetUtils.getHttpEntity(URLMananeger.BOOK_WRITER_SEARCHR_ULR,
					NetUtils.GET, keys, values);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		GeneralResult all = gson.fromJson(s, new TypeToken<GeneralResult>() {
		}.getType());
		if (all != null)
			return all;
		else
			return null;
	}
}
