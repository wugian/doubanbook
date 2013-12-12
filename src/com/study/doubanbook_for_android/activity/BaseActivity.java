package com.study.doubanbook_for_android.activity;

import com.study.doubanbook_for_android.imagedownloader.ImageDownloader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BaseActivity extends Activity {

	public final static int PAGE_COUNT = 10;
	protected ImageDownloader imageDownloader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		logD("TTT", "base Activity oncreate");
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
}
