package com.study.doubanbook_for_android.activity;

import com.study.doubanbook_for_android.R;

import android.os.Bundle;

/**
 * 关于我们,仅作为信息显示
 * @author tezuka-pc
 *
 */
public class AboutUsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_about_us);
		setNavagator("关于我们", 0);
	}
}
