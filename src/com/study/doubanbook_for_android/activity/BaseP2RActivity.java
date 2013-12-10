package com.study.doubanbook_for_android.activity;

import java.util.ArrayList;
import java.util.List;

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
import com.study.doubanbook_for_android.model.BookItem;

public class BaseP2RActivity<T> extends BaseActivity implements
		OnRefreshListener<ListView>, OnItemClickListener {

	PullToRefreshListView p2r_lv;
	protected ArrayList<T> dataList = new ArrayList<T>();
	public static final int REQUEST_CODE_PUBLISH = 0;
	protected BaseAdapter adapter;
	protected int pageIndex = 1;

	void initP2RLv() {
		p2r_lv = (PullToRefreshListView) this.findViewById(R.id.p2r_lv);
		ListView listview = p2r_lv.getRefreshableView();
		listview.setVerticalFadingEdgeEnabled(false);
		p2r_lv.setMode(Mode.PULL_FROM_END);
		p2r_lv.setShowIndicator(false);
		p2r_lv.setOnItemClickListener(this);
		p2r_lv.setOnRefreshListener(this);
		p2r_lv.setAdapter(getAdapter());
	}

	/**
	 * 添加数据
	 * 
	 * @param data
	 */
	void addData(List<T> data) {
		refreshCompleted();
		dataList.addAll(data);
		// TODO DEBUG　WHY
		// 重新绑定一次不然不会显示数据,现在取消没有影响
		// p2r_lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	// 重新刷新数据
	protected void reUpdateData() {
		dataList.clear();
		fetchData();
	}

	protected void refreshCompleted() {
		p2r_lv.onRefreshComplete();
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
