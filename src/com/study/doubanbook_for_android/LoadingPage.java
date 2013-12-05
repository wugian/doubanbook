package com.study.doubanbook_for_android;

import android.support.v4.app.FragmentActivity;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.study.doubanbook_for_android.R.id;
import com.study.doubanbook_for_android.fragment.LoginFragment_;

@EActivity(R.layout.activity_loading_page)
public class LoadingPage extends FragmentActivity {

	@AfterViews
	void init() {
		getSupportFragmentManager().beginTransaction()
				.add(id.container_lyt, new LoginFragment_()).commit();
		// String s = "lovely";
		// String s1 = "test";
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
