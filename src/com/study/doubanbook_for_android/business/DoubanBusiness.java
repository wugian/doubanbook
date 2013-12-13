package com.study.doubanbook_for_android.business;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.activity.BaseActivity;
import com.study.doubanbook_for_android.api.NetUtils;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.model.URLMananeger;

public class DoubanBusiness {
	// TODO 修改整个方法,利用线程,回调,得到
	// 得到字符串,转化成MODEL时没有TRY CATCH,怎样判断是否是错误信息,先变成WRONGMSG
	// MODEL?判断CODE?添加TAG,CODE==0转化成对应模型否则转化成WRONGMSG
	public void getSearchList(final String q, final int start,
			final AsynCallback<GeneralResult> callback) {
		new Thread() {
			public void run() {
				boolean isRightModle = false;
				WrongMsg wrongMsg = null;
				GeneralResult result = null;
				Gson gson = new Gson();
				String s = "";
				List<String> keys = new ArrayList<String>();
				List<String> values = new ArrayList<String>();

				// init keys
				keys.add("q");
				keys.add("start");
				keys.add("count");
				// init values
				try {
					// change the encode when necessary
					values.add(URLEncoder.encode(q, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				values.add(start + "");
				values.add(BaseActivity.PAGE_COUNT + "");

				callback.onStart();
				s = NetUtils.getHttpEntity(
						URLMananeger.BOOK_WRITER_SEARCHR_ULR, 3, keys, values);
				Log.d("NET", s);

				try {
					wrongMsg = gson.fromJson(s, new TypeToken<WrongMsg>() {
					}.getType());
					callback.onFailure(wrongMsg);
				} catch (Exception e) {
					isRightModle = true;
				}
				if (!isRightModle) {
					result = gson.fromJson(s, new TypeToken<GeneralResult>() {
					}.getType());
					callback.onSuccess(result);
				}
				callback.onDone();

			};
		}.start();
	}
}
