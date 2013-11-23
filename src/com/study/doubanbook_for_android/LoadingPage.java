package com.study.doubanbook_for_android;

import android.app.Activity;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_loading_page)
public class LoadingPage extends Activity {

	@AfterViews
	void test() {
		String s = "lovely";
		String s1 = "test";
		// switch (s1) {
		// case "lovely":
		// System.out.println("lovely");
		// break;
		// case "test":
		// System.out.println("test");
		// break;
		//
		// default:
		// break;
		// }

	}
}
