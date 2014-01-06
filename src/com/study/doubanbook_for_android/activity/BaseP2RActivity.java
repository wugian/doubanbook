package com.study.doubanbook_for_android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

/**
 * 
 * 需要重写的方法有,fetchdata,sendMessage,seleHandleMsg,selfhandleMsg存在BUG updatatime
 * 2013-12-13
 * 
 * @author tezuka-pc
 * 
 * @param <T>
 */
public class BaseP2RActivity<T> extends BaseActivity implements
		OnRefreshListener<ListView>, OnItemClickListener {

	PullToRefreshListView p2r_lv;
	protected ArrayList<T> dataList = new ArrayList<T>();
	public static final int REQUEST_CODE_CHANGED = 1;
	protected BaseAdapter adapter;
	protected int pageIndex = 0;

	public static final int SUCCESS = 0;
	public static final int FAILURE = 1;

	// thread
	private MessageHandler msgHandler;

	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			selfHandleMsg(msg);
		}
	}

	/**
	 * 利用MESSAGEHANDLER发送消息到UI线程
	 * 
	 * @param b
	 * @param status
	 */
	void sendMessage(Object b, int status) {
		Message message = Message.obtain();
		message.arg1 = status;
		message.obj = b;
		msgHandler.sendMessage(message);
	}

	/**
	 * 怎样处理接受到的消息,自己覆盖方法判断使用
	 * 
	 * @param msg
	 */
	public void selfHandleMsg(Message msg) {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化下拉列表和线程,这是以前写的没有去修改,可以不使用这个基类里的纯种的
	 */
	void initP2RLvAndThread() {
		// init p2r
		p2r_lv = (PullToRefreshListView) this.findViewById(R.id.p2r_lv);
		ListView listview = p2r_lv.getRefreshableView();
		listview.setVerticalFadingEdgeEnabled(false);
		p2r_lv.setMode(Mode.PULL_FROM_END);
		p2r_lv.setShowIndicator(false);
		p2r_lv.setOnItemClickListener(this);
		p2r_lv.setOnRefreshListener(this);
		p2r_lv.setAdapter(getAdapter());

		// init msgHandler
		Looper looper = Looper.myLooper();
		msgHandler = new MessageHandler(looper);
	}

	/**
	 * 添加数据
	 * 
	 * @param data
	 */
	void addData(List<T> data) {
		refreshCompleted();
		if (data != null) {
			dataList.addAll(data);
			// TODO DEBUG　WHY
			// 重新绑定一次不然不会显示数据,现在取消没有影响
			// p2r_lv.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}

	// 重新刷新数据
	protected void reUpdateData() {
		pageIndex = 0;
		dataList.clear();
		fetchData();
	}

	/**
	 * 刷新结束完成
	 */
	protected void refreshCompleted() {
		p2r_lv.onRefreshComplete();
	}

	/**
	 * 监听是否要刷新当前页面的数据
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK
				&& requestCode == REQUEST_CODE_CHANGED)
			if (data.getBooleanExtra("isChanged", false)){
				reUpdateData();
				toast("正在刷新数据");
			}
	}

	// ------------------------------------------------

	/**
	 * 从服务器获取消息.
	 */
	public void fetchData() {

	}

	/**
	 * 单项单击事件,其中position 从0开始
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

	}

	/**
	 * 下拉到底的操作
	 */
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
