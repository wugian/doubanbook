package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.adapter.BookAdapter;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.BookItem;
import com.study.doubanbook_for_android.model.GeneralResult;

public class CopyOfBookListsActivity extends BaseP2RActivity<BookItem> {
	private MessageHandler msgHandler;
	String searchContent = "";
	protected int pageIndex = 0;
	GeneralResult result;
	DoubanBusiness db = new DoubanBusiness();

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
		initP2RLvAndThread();
		searchContent = getIntent().getStringExtra("searchContent");
		// if (searchContent.equals("")) {
		// finish();
		// } else
		fetchData();
	}

	@Override
	public void fetchData() {
		super.fetchData();
		db.getSearchList(searchContent, pageIndex * PAGE_COUNT + 1,
				new AsynCallback<GeneralResult>() {
					public void onSuccess(GeneralResult data) {
						pageIndex++;
						Message message = Message.obtain();
						message.obj = data;
						msgHandler.sendMessage(message);
					};

					public void onFailure(
							com.study.doubanbook_for_android.api.WrongMsg caught) {
					};
				});

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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		super.onItemClick(arg0, arg1, position, arg3);
		Intent intent = new Intent(this, BookDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("bookItem", dataList.get(position - 1));
		intent.putExtras(bundle);

		startActivity(intent);
	}
}