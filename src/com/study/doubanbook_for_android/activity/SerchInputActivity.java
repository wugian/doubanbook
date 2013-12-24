package com.study.doubanbook_for_android.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.business.DoubanBusiness;

public class SerchInputActivity extends BaseActivity {

	EditText search_et;
	Button search_btn;
	TextView clear_tv;
	private Context context;
	private Button authBtn;
	private TextView readerSearch;
	private TextView bookSearch;
	private static final int SEARCH_BOOK = 0;// search book flag
	private static final int SEARCH_READER = 1;// search reader flag

	private int flag = SEARCH_BOOK;// search flag

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_serch_input);

		context = this;
		findViews();
		initWidgets();
		initListners();

		// auto auth
		DoubanBusiness db = new DoubanBusiness(context);
		db.auth();
	}

	@Override
	void findViews() {
		super.findViews();
		search_et = (EditText) this.findViewById(R.id.serchContent_et);
		search_btn = (Button) this.findViewById(R.id.search_btn);
		clear_tv = (TextView) this.findViewById(R.id.clear_tv);
		authBtn = (Button) this.findViewById(R.id.authBtn);
		bookSearch = (TextView) this.findViewById(R.id.bookSearch_tv);
		readerSearch = (TextView) this.findViewById(R.id.readerSearch_tv);

	}

	@Override
	void initWidgets() {
		super.initWidgets();
		clear_tv.setVisibility(View.GONE);
		// bookSearch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		readerSearch.setTextColor(Color.GRAY);
	}

	@Override
	void initListners() {
		super.initListners();

		search_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println(flag);
				if (flag == SEARCH_BOOK) {
					Intent intent = new Intent();
					intent.setClass(context, BookListsActivity.class);
					String s = getText(search_et);
					intent.putExtra("searchContent", s);
					startActivity(intent);
				} else if (flag == SEARCH_READER) {
					Intent intent = new Intent();
					intent.setClass(context, UserListActivity.class);
					String s = getText(search_et);
					intent.putExtra("searchContent", s);
					startActivity(intent);
				}
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

		bookSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// bookSearch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//
				// 下划线
				// readerSearch.getPaint().setFlags(Paint.););
				bookSearch.setTextColor(Color.BLACK);
				readerSearch.setTextColor(Color.GRAY);
				search_et.setHint(getResources().getString(
						R.string.search_book_hint));
				flag = SEARCH_BOOK;
				System.out.println(flag);
			}
		});

		readerSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// readerSearch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//
				// 下划线
				// readerSearch.getPaint().setFlags(Paint.););

				bookSearch.setTextColor(Color.GRAY);
				readerSearch.setTextColor(Color.BLACK);
				search_et.setHint(getResources().getString(
						R.string.search_reader_hint));
				flag = SEARCH_READER;
				System.out.println(flag);
			}
		});

	}

	boolean test = false;
}
