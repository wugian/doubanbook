package com.study.doubanbook_for_android.activity;

import android.os.Bundle;

import com.study.doubanbook_for_android.R.layout;
import com.study.doubanbook_for_android.model.AuthorUser;

public class UserDetailActivity extends BaseActivity {

	private AuthorUser userDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.a_user_detail);

	}
}
