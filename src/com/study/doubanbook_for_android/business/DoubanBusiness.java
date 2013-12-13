package com.study.doubanbook_for_android.business;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.api.NetUtils;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.model.URLMananeger;

public class DoubanBusiness {

	// TODO 修改整个方法,利用线程,回调,得到
	public GeneralResult getSearchList(String q, int start, int count) {
		List<String> keys = new ArrayList<String>();
		GeneralResult all = null;
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
		s = NetUtils.getHttpEntity(URLMananeger.BOOK_WRITER_SEARCHR_ULR, 3,
				keys, values);
		Log.d("NET", s);
		// 得到字符串,转化成MODEL时没有TRY CATCH,怎样判断是否是错误信息,先变成WRONGMSG
		// MODEL?判断CODE?添加TAG,CODE==0转化成对应模型否则转化成WRONGMSG
		Gson gson = new Gson();
		try {
			all = gson.fromJson(s, new TypeToken<GeneralResult>() {
			}.getType());
		} catch (Exception e) {
			WrongMsg alls = gson.fromJson(s, new TypeToken<WrongMsg>() {
			}.getType());
		}

		if (all != null)
			return all;
		else
			return null;
	}
}
