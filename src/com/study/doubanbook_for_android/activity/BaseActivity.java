package com.study.doubanbook_for_android.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.imagedownloader.ImageDownloader;
import com.study.doubanbook_for_android.utils.DebugUtils;
import com.study.doubanbook_for_android.utils.ToastUtils;

public class BaseActivity extends Activity {

	public final static int PAGE_COUNT = 10;
	protected ImageDownloader imageDownloader;
	protected Context context;
	private TextView pageTile_tv;
	private Button back_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bar_all);
		DebugUtils.d("TTT", "base Activity oncreate");
		pageTile_tv = (TextView) findViewById(R.id.pageTitle_tv);
		back_btn = (Button) findViewById(R.id.back_btn);

		back_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		context = this;
		imageDownloader = new ImageDownloader(this);
	}

	void setTitle(String s) {
		pageTile_tv.setText(s);
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
