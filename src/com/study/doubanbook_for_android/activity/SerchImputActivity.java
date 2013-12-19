package com.study.doubanbook_for_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;

public class SerchImputActivity extends BaseActivity {

	EditText search_et;
	Button search_btn;
	TextView clear_tv;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_serch_input);
		context = this;
		findViews();
		initWidgets();
		initListners();
	}

	@Override
	void findViews() {
		super.findViews();
		search_et = (EditText) this.findViewById(R.id.serchContent_et);
		search_btn = (Button) this.findViewById(R.id.search_btn);
		clear_tv = (TextView) this.findViewById(R.id.clear_tv);
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		clear_tv.setVisibility(View.GONE);
		search_et.setText("乔布斯");
	}

	@Override
	void initListners() {
		super.initListners();

		search_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(context, BookListsActivity.class);
				String s = getText(search_et);
				intent.putExtra("searchContent", s);
				startActivity(intent);
			}
		});
		search_et.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = getText(search_et);
				if (!str.toString().equals("")) {
					clear_tv.setVisibility(View.VISIBLE);
				} else {
					clear_tv.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		clear_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				search_et.setText("");
			}
		});

	}
}
