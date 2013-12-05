package com.study.doubanbook_for_android.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.OnActivityResult;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.study.doubanbook_for_android.R;

@EFragment(R.layout.f_comment_list)
public class BaseP2RListFragment<T> extends BaseFragment implements
		OnRefreshListener<ListView>, OnItemClickListener {

	public static final int REQUEST_CODE_PUBLISH = 0;

	protected boolean isManageMode = false;
	protected boolean isDelMode = false;
	protected ArrayList<T> dataList = new ArrayList<T>();

	protected int pageIndex = 1;

	// /@StringRes
	String manage = "ma", delete = "de";

	// 动态列表
	@ViewById
	protected PullToRefreshListView p2r_lv;
	protected BaseAdapter adapter;

	protected ArrayList<Integer> deletePosList;

	// ------------------------------------------------
	// init

	@Override
	protected void initData() {
		super.initData();
	}

	@Override
	protected void initWidgets() {
		super.initWidgets();
		initListView();
		fetchData();
	}

	protected void initListView() {
		ListView threndLv = p2r_lv.getRefreshableView();
		threndLv.setVerticalFadingEdgeEnabled(false);

		p2r_lv.setMode(Mode.PULL_FROM_END);
		p2r_lv.setShowIndicator(false);
		p2r_lv.setOnItemClickListener(this);
		p2r_lv.setOnRefreshListener(this);
		p2r_lv.setAdapter(getAdapter());
	}

	// ------------------------------------------------

	/**
	 * 从服务器获取消息.
	 */
	public void fetchData() {

	}

	/**
	 * 添加数据
	 * 
	 * @param data
	 */
	void addData(List<T> data) {
		this.dataList.addAll(data);
		notifyUpdate();
	}

	// ------------------------------------------------

	/**
	 * 由子类覆盖执行发布新条目动作
	 */
	public void onPublish() {
	}

	/**
	 * 由子类覆盖执行删除动作, 此方法默认获取data中要删除数据的位置
	 */
	protected void performDelete() {
		System.out
				.println("*****************in performdelete********************");
	}

	// 顶部右键编辑模式切换
	protected void changeModeAndSetText() {
		isManageMode = !isManageMode;
		if (isManageMode)
			done_btn.setText(delete);
		else {
			done_btn.setText(manage);
		}
	}

	// 发布结果
	@OnActivityResult(REQUEST_CODE_PUBLISH)
	protected void onPublishResult(int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (data != null)
				if (data.getBooleanExtra("isPublished", false))
					reUpdateData();

		}
	}

	// 重新刷新数据
	protected void reUpdateData() {
		pageIndex = 1;
		dataList.clear();
		fetchData();
	}

	// 在下拉刷新
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		if (hasMoreData()) {
			fetchData();
		} else {
			setRefreshCompleted();
			// showToastMsg("没有更多数据了");
		}
	}

	// 是否有下一页数据
	protected boolean hasMoreData() {
		return true;
	}

	// ------------------------------
	// other
	/**
	 * 由子类覆盖响应条目点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}

	@UiThread
	protected void setRefreshCompleted() {
		p2r_lv.onRefreshComplete();
	}

	String getDelStr() {
		StringBuffer delStr = new StringBuffer();
		for (int k : deletePosList) {
			delStr.append(k).append(",");
		}
		return delStr.toString();
	}

	// 通知adatper数据转换
	@UiThread
	protected void notifyUpdate() {
		getAdapter().notifyDataSetChanged();
	}

	private BaseAdapter getAdapter() {
		return adapter;
	}

}
