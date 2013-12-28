package com.study.doubanbook_for_android.activity;

import org.apache.http.impl.conn.ProxySelectorRoutePlanner;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.Annotations;
import com.study.doubanbook_for_android.model.GeneralNoteResult;
import com.study.doubanbook_for_android.utils.DebugUtils;

public class NoteEditActivity extends BaseActivity {

	Annotations annotations = null;
	EditText content_et;
	EditText page_et;
	EditText chapName_et;
	CheckBox privacy_cb;
	private Button ok_btn;
	DoubanBusiness doubanBusiness = new DoubanBusiness(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_book_note_edit);
		DebugUtils.e("CLASS", getClass().getName());
		setNavagator("添加笔记");
		findViews();
		initDatas();
		initWidgets();
		initListners();
	}

	@Override
	void findViews() {
		super.findViews();
		content_et = (EditText) findViewById(R.id.comment_et);
		page_et = (EditText) findViewById(R.id.page_et);
		chapName_et = (EditText) findViewById(R.id.chapName_et);
		privacy_cb = (CheckBox) findViewById(R.id.privacey_cb);
		ok_btn = (Button) findViewById(R.id.ok_btn);
	}

	@Override
	void initDatas() {
		super.initDatas();
		try {
			annotations = (Annotations) getIntent().getSerializableExtra(
					"annotations");
		} catch (Exception e) {
			DebugUtils.d("NET", e.getMessage());
		}
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		if (annotations != null) {
			content_et.setText(annotations.getContent());
			if (notZero(annotations.getPage_no())) {
				page_et.setText(annotations.getPage_no());
			}
			if (notNull(annotations.getChapter()))
				chapName_et.setText(annotations.getChapter());
			// if(annotations.getPrivacy())
		}
	}

	@Override
	void initListners() {
		super.initListners();
		ok_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String content = getText(content_et);
				String page = getText(page_et);
				String chapName = getText(chapName_et);
				boolean privace = privacy_cb.isChecked();
				if (!notNull(content) || content.length() <= 15) {
					toast("笔记内容不能为空并字数不少于15字");
					return;
				}
				if (!notNull(chapName) && notNull(page)) {
					toast("章节名字和页数不能同时为空");
					return;
				}
				doubanBusiness.writeNote(
						String.valueOf(annotations.getBook_id()), content,
						page, chapName, privace,
						new AsynCallback<GeneralNoteResult>() {
							@Override
							public void onSuccess(GeneralNoteResult data) {
								super.onSuccess(data);
								System.out.println("success");
							}
							@Override
							public void onFailure(WrongMsg caught) {
								super.onFailure(caught);
								System.out.println("failure");
							}
						});
			}
		});
	}
}
