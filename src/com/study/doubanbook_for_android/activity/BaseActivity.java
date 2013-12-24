package com.study.doubanbook_for_android.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.study.doubanbook_for_android.imagedownloader.ImageDownloader;
import com.study.doubanbook_for_android.utils.DebugUtils;
import com.study.doubanbook_for_android.utils.ToastUtils;

public class BaseActivity extends Activity {

	public final static int PAGE_COUNT = 10;
	protected ImageDownloader imageDownloader;
	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DebugUtils.d("TTT", "base Activity oncreate");
		context = this;
		imageDownloader = new ImageDownloader(this);
	}

	void findViews() {
		DebugUtils.d("TTT", "base Activity findview");
	}

	void initDatas() {
		DebugUtils.d("TTT", "base Activity initDatas");
	}

	void initWidgets() {
		DebugUtils.d("TTT", "base Activity initwidgets");
	}

	void initListners() {
		DebugUtils.d("TTT", "base Activity initlisternser");
	}

	String getText(TextView tv) {
		return tv.getText().toString().trim();
	}

	/**
	 * 不为0
	 * 
	 * @param k
	 * @return
	 */
	boolean notZero(int k) {
		if (k != 0)
			return true;
		else
			return false;
	}

	/**
	 * 不为空并不为""空串
	 * 
	 * @param k
	 * @return
	 */
	boolean notNull(String k) {
		if (k != null && !k.equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 显示简单文字提示
	 * 
	 * @param msg
	 */
	void toast(String msg) {
		ToastUtils.toast(context, msg);
	}
}
