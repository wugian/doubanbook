package com.study.doubanbook_for_android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.adapter.BookAdapter;
import com.study.doubanbook_for_android.model.BookItem;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.utils.ModelUtils;

public class BookListsActivity extends BaseP2RActivity<BookItem> {
	private MessageHandler msgHandler;
	String serchContent = "";
	protected int pageIndex = 1;
	GeneralResult result;

	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			// 处理收到的消 息，把天气信息显示在title上
			result = (GeneralResult) (msg.obj);
			addData(result.getBooks());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_comment_list);
		adapter = new BookAdapter(dataList, this);
		Looper looper = Looper.myLooper();
		msgHandler = new MessageHandler(looper);
		initP2RLv();
		serchContent = getIntent().getStringExtra("searchContent");
		if (serchContent.equals("")) {
			finish();
		} else
			fetchData();
	}

	@Override
	public void fetchData() {
		super.fetchData();
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
				}
			}
		}.start();
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		super.onRefresh(refreshView);
		if (pageIndex < pageIndex * PAGE_COUNT)
			fetchData();
		else {
			refreshCompleted();
		}

	}
}