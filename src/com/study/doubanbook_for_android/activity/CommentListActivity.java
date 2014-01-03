package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.adapter.BookAdapter;
import com.study.doubanbook_for_android.adapter.CommentAdapter;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.utils.DebugUtils;
import com.study.doubanbook_for_android.utils.ShowErrorUtils;
import com.study.doubanbook_for_android.xmlpaser.CommentReslult;
import com.study.doubanbook_for_android.xmlpaser.Entry;

public class CommentListActivity extends BaseP2RActivity<Entry> {

	String isbn = "";
	CommentReslult result;
	DoubanBusiness db = new DoubanBusiness(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DebugUtils.e("CLASS", getClass().getName());
		setContentView(R.layout.f_comment_list);
		adapter = new CommentAdapter(dataList, this);
		initP2RLvAndThread();
		isbn = getIntent().getStringExtra("isbn10");
		setNavagator("'" + isbn + "' 的搜索结果");
		fetchData();
	}

	@Override
	public void selfHandleMsg(Message msg) {
		int arg1 = msg.arg1;
		switch (arg1) {
		case SUCCESS:
			result = (CommentReslult) (msg.obj);
			addData(result.getEntry());
			break;
		case FAILURE:
			ShowErrorUtils.showWrongMsg(context, msg);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void fetchData() {
		super.fetchData();
		db.getCommentList(isbn, pageIndex * PAGE_COUNT,
				new AsynCallback<CommentReslult>() {
					public void onSuccess(CommentReslult data) {
						pageIndex++;
						sendMessage(data, SUCCESS);
					};

					public void onFailure(
							com.study.doubanbook_for_android.api.WrongMsg caught) {
						sendMessage(caught, FAILURE);
					};
				});
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		super.onRefresh(refreshView);
		if (pageIndex < pageIndex * PAGE_COUNT) {
			fetchData();
			refreshCompleted();
		} else {
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
		startActivityForResult(intent, REQUEST_CODE_CHANGED);
	}
}