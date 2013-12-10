package com.study.doubanbook_for_android.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.adapter.BookAdapter;
import com.study.doubanbook_for_android.model.BookItem;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.utils.ModelUtils;

public class BookListActivity extends BaseActivity implements
		OnRefreshListener<ListView>, OnItemClickListener {

	PullToRefreshListView p2r_lv;
	protected ArrayList<BookItem> booklists = new ArrayList<BookItem>();
	protected BookAdapter bookAdapter;
	private MessageHandler msgHandler;
	String serchContent = "book";
	protected int pageIndex = 1;

	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			// 处理收到的消 息，把天气信息显示在title上
			GeneralResult result = (GeneralResult) (msg.obj);
			addData(result.getBooks());
		}
	}

	void initData() {
		bookAdapter = new BookAdapter(booklists, this);
		Looper looper = Looper.myLooper();
		msgHandler = new MessageHandler(looper);
		logD("TTT", "booklist init datas");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_comment_list);
		initData();
		findView();
		initListView();
		fetchData();
	}

	void findView() {
		p2r_lv = (PullToRefreshListView) findViewById(R.id.p2r_lv);
	}

	protected void initListView() {
		ListView listview = p2r_lv.getRefreshableView();
		listview.setVerticalFadingEdgeEnabled(false);

		p2r_lv.setMode(Mode.PULL_FROM_END);
		p2r_lv.setShowIndicator(false);
		p2r_lv.setOnItemClickListener(this);
		p2r_lv.setOnRefreshListener(this);
		p2r_lv.setAdapter(bookAdapter);
	}

	/**
	 * 添加数据
	 * 
	 * @param data
	 */
	void addData(List<BookItem> data) {
		p2r_lv.onRefreshComplete();
		booklists.addAll(data);
		// 重新绑定一次不然不会显示数据
		p2r_lv.setAdapter(bookAdapter);
		bookAdapter.notifyDataSetChanged();
	}

	// 重新刷新数据
	protected void reUpdateData() {
		booklists.clear();
		fetchData();
	}

	// ------------------------------------------------

	/**
	 * 从服务器获取消息.
	 */
	public void fetchData() {
		new Thread() {
			public void run() {
				GeneralResult aal = ModelUtils.getBookList(serchContent,
						pageIndex * PAGE_COUNT);
				if (aal != null) {
					pageIndex++;
					Message message = Message.obtain();
					message.obj = aal;
					// 通过Handler发布携带有天 气情况的消息
					msgHandler.sendMessage(message);
					System.out.println(aal.getBooks().size());
				}
			};
		}.start();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		fetchData();
	}

}
