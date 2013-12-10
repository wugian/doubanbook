package com.study.doubanbook_for_android.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.study.doubanbook_for_android.R;

public class BaseP2RActivity<T> extends BaseActivity implements
		OnRefreshListener<ListView>, OnItemClickListener {

	PullToRefreshListView p2r_lv;
	protected ArrayList<T> dataList = new ArrayList<T>();
	public static final int REQUEST_CODE_PUBLISH = 0;
	protected BaseAdapter adapter;
	protected int pageIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_comment_list);
		System.out.println("oncreate basep2r");
	}

	@Override
	void findViews() {
		super.findViews();
		p2r_lv = (PullToRefreshListView) findViewById(R.id.p2r_lv);
		System.out.println("oncreate findview ");

		initListView();
		System.out.println("oncreate init widigets p24");
	}

	protected void initListView() {
		if (p2r_lv == null) {
			findViews();
		}
		ListView listview = p2r_lv.getRefreshableView();
		listview.setVerticalFadingEdgeEnabled(false);
		System.out.println("init list view");

		p2r_lv.setMode(Mode.PULL_FROM_END);
		p2r_lv.setShowIndicator(false);
		p2r_lv.setOnItemClickListener(this);
		p2r_lv.setOnRefreshListener(this);
		p2r_lv.setAdapter(getAdapter());
	}

	// 重新刷新数据
	protected void reUpdateData() {
		pageIndex = 1;
		dataList.clear();
		fetchData();
	}

	// ------------------------------------------------

	/**
	 * 从服务器获取消息.
	 */
	public void fetchData() {

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {

	}

	// 通知adatper数据转换
	protected void notifyUpdate() {
		getAdapter().notifyDataSetChanged();
	}

	private BaseAdapter getAdapter() {
		return adapter;
	}
}
