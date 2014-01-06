package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.utils.PrefUtils;

public class SettingActivity extends BaseActivity {

	private ToggleButton open_tog_btn;
	private TextView aboutUs_tv;
	boolean isInit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_seting);
		setNavagator("设置", 0);
		findViews();
		initListners();
		isInit = true;
		initWidgets();
		isInit = false;
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		open_tog_btn.setChecked(PrefUtils.getAutoLogin(context));
	}

	@Override
	void findViews() {
		super.findViews();
		open_tog_btn = (ToggleButton) findViewById(R.id.open_tog_btn);
		aboutUs_tv = (TextView) findViewById(R.id.aboutUs_tv);
	}

	@Override
	void initListners() {
		super.initListners();
		open_tog_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (!isInit) {
					if (isChecked) {
						toast("打开自动登录");
					} else {
						toast("关闭自动登录");
					}
					PrefUtils.saveAutoLogin(context, isChecked);
				}
			}
		});
		aboutUs_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, AboutUsActivity.class);
				startActivity(intent);
			}
		});
	}
}
