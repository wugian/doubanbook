//package com.study.doubanbook_for_android.activity;
//
//import java.util.ArrayList;
//
//import android.os.Bundle;
//import android.widget.BaseAdapter;
//
//import com.study.doubanbook_for_android.R;
//import com.study.doubanbook_for_android.adapter.BookAdapter;
//import com.study.doubanbook_for_android.model.BookItem;
//import com.study.doubanbook_for_android.model.GeneralResult;
//import com.study.doubanbook_for_android.utils.ModelUtils;
//
//public class CopyOfBookListActivity extends BaseP2RActivity<BookItem> {
//
//	ArrayList<BookItem> books = new ArrayList<BookItem>();
//
//	@Override
//	void initDatas() {
//		super.initDatas();
//		adapter = new BookAdapter(books, this);
//		logD("TTT", "booklist init datas");
//	}
//
//	@Override
//	void initWidgets() {
//		super.initWidgets();
//	}
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.f_comment_list);
//		logD("TTT", "booklist oncrete");
//		initDatas();
//		findView();
//		initListView();
//		fetchData();
//
//	}
//
//	@Override
//	public void fetchData() {
//		super.fetchData();
//		String s;
//		new Thread() {
//			public void run() {
//				GeneralResult<BookItem> aal = ModelUtils.getBookList("ss");
//				if (aal != null) {
//					addData(aal.getBooks());
//					System.out.println(aal.getBooks().size());
//				}
//			};
//		}.start();
//	}
//
//	private BaseAdapter getAdapter() {
//		return adapter;
//	}
// }
