package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.adapter.UserListAdapter;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.AuthorUser;
import com.study.doubanbook_for_android.model.GeneralUserResult;

public class UserListActivity extends BaseP2RActivity<AuthorUser> {

	String searchContent = "";
	GeneralUserResult result;
	DoubanBusiness db = new DoubanBusiness(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_comment_list);
		pageIndex = 0;
		adapter = new UserListAdapter(dataList, this);
		initP2RLvAndThread();
		searchContent = getIntent().getStringExtra("searchContent");
		fetchData();
	}

	@Override
	public void selfHandleMsg(Message msg) {
		int arg1 = msg.arg1;
		switch (arg1) {
		case SUCCESS:
			result = (GeneralUserResult) (msg.obj);
			addData(result.getUsers());
			break;
		case FAILURE:
			WrongMsg w = (WrongMsg) (msg.obj);
			Toast.makeText(this,
					w.getCode() + " " + w.getMsg() + " " + w.getRequest(),
					Toast.LENGTH_SHORT).show();
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void fetchData() {
		super.fetchData();
		db.searchUser(searchContent, pageIndex * PAGE_COUNT,
				new AsynCallback<GeneralUserResult>() {
					public void onSuccess(GeneralUserResult data) {
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
		Intent intent = new Intent(this, UserDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("userDetail", dataList.get(position - 1));
		intent.putExtras(bundle);
		startActivity(intent);
	}
}