package com.study.doubanbook_for_android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.model.Entry;

public class CommentWriteActivity extends BaseActivity {

	EditText comment_et;
	Button ok, cancle;
	Entry entryItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_write_comment);
		initDatas();
		if (entryItem != null)
			setNavagator("修改评论");
		else
			setNavagator("写评论");
		findViews();
		initWidgets();
	}

	@Override
	void initDatas() {
		super.initDatas();
		entryItem = (Entry) getIntent().getSerializableExtra("entryItem");
	}

	@Override
	void findViews() {
		super.findViews();
	}

	@Override
	void initWidgets() {
		super.initWidgets();
	}
}
