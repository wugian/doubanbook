package com.study.doubanbook_for_android.activity;

import java.util.ArrayList;

import android.os.Bundle;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.adapter.BookAdapter;
import com.study.doubanbook_for_android.model.BookItem;

public class BookListActivity extends BaseP2RActivity<BookItem> {

	ArrayList<BookItem> books = new ArrayList<BookItem>();

	@Override
	void initDatas() {
		super.initDatas();
		adapter = new BookAdapter(books, this);
	}

	@Override
	void initWidgets() {
		super.initWidgets();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_comment_list);

	}

}
