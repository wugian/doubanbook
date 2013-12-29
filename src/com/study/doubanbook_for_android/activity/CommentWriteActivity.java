package com.study.doubanbook_for_android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.study.doubanbook_for_android.R;

public class CommentWriteActivity extends BaseActivity {

	EditText comment_et;
	Button ok, cancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_write_comment);
		findViews();
	}
	@Override
	void findViews() {
		super.findViews();
	}
}
