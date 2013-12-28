package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.model.Annotations;
import com.study.doubanbook_for_android.utils.DebugUtils;

public class NoteAndUserDetailActivity extends BaseActivity {

	Annotations annotations;
	ImageView bigAvatar_iv;
	TextView userName_tv;
	TextView noteHome_tv;
	Button showQrCode_btn;
	TextView comment_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_note_user_detail);
		DebugUtils.e("CLASS", getClass().getName());
		initDatas();
		findViews();
		setNavagator(annotations.getBook().getTitle() + "的评论");
		setRightButton();
		initWidgets();
		initListners();
	}

	@Override
	void initRightListener() {
		super.initRightListener();
		// TODO add note detail activity and xml
		Intent intent = new Intent(context, NoteEditActivity.class);
		intent.putExtra("annotations", annotations);
		startActivity(intent);
		// TODO for result
		// startActivityForResult(intent, 100);

	}

	@Override
	void initDatas() {
		super.initDatas();
		annotations = (Annotations) getIntent().getSerializableExtra(
				"annotations");
	}

	@Override
	void findViews() {
		super.findViews();
		bigAvatar_iv = (ImageView) findViewById(R.id.bigAvatar_iv);
		userName_tv = (TextView) findViewById(R.id.userName_tv);
		noteHome_tv = (TextView) findViewById(R.id.noteHome_tv);
		showQrCode_btn = (Button) findViewById(R.id.showQrCode_btn);
		comment_tv = (TextView) findViewById(R.id.comment_tv);
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		imageDownloader.download(
				annotations.getAuthor_user().getLarge_avatar(), bigAvatar_iv,
				null);
		userName_tv.setText(annotations.getAuthor_user().getName());
		noteHome_tv.setText(annotations.getAuthor_user().getAlt());
		comment_tv.setText(annotations.getContent());
	}

	@Override
	void initListners() {
		super.initListners();
	}
}
