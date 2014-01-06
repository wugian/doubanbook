package com.study.doubanbook_for_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.R.anim;

/**
 * 着陆页面,写的还行,可以作为借鉴
 * @author tezuka-pc
 *
 */
public class LoadingActivity extends BaseActivity implements Runnable {

	ViewGroup start_lyt;

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_loadpage);
		start_lyt = (ViewGroup) findViewById(R.id.start_lyt);
		startJumpTask();
	};

	void startJumpTask() {
		start_lyt.postDelayed(this, 3000);
	}

	protected void jumpMainActivity() {
		Intent intent = new Intent(this, SerchInputActivity.class);
		overridePendingTransition(anim.fade_in, anim.fade_out);
		startActivity(intent);
		((Activity) context).finish();
	}

	public void start_lyt(View v) {
		start_lyt.removeCallbacks(this);
		jumpMainActivity();
	}

	@Override
	public void run() {
		jumpMainActivity();
	}
}
