package com.study.doubanbook_for_android.activity;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findViews();
		initWidgets();
		initListners();
	}

	void findViews() {

	}

	void initDatas() {

	}

	void initWidgets() {

	}

	void initListners() {

	}

}
