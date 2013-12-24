package com.study.doubanbook_for_android.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.study.doubanbook_for_android.imagedownloader.ImageDownloader;

public class BaseActivity extends Activity {

	public final static int PAGE_COUNT = 10;
	protected ImageDownloader imageDownloader;
	protected Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		logD("TTT", "base Activity oncreate");
		context = this;
		imageDownloader = new ImageDownloader(this);
	}

	void findViews() {
		logD("TTT", "base Activity findview");
	}

	void initDatas() {
		logD("TTT", "base Activity initDatas");
	}

	void initWidgets() {
		logD("TTT", "base Activity initwidgets");
	}

	void initListners() {
		logD("TTT", "base Activity initlisternser");
	}

	void logD(String tag, String msg) {
		Log.d(tag, msg);
	}

	String getText(TextView tv) {
		return tv.getText().toString().trim();
	}

	boolean notZero(int k) {
		if (k != 0)
			return true;
		else
			return false;
	}

	boolean notNull(String k) {
		if (k != null)
			return true;
		else
			return false;
	}

	void showToast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
