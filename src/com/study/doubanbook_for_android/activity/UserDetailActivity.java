package com.study.doubanbook_for_android.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.R.layout;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.imagedownloader.ImageDownloader;
import com.study.doubanbook_for_android.model.AuthorUser;
import com.study.doubanbook_for_android.model.CollectionItem;
import com.study.doubanbook_for_android.model.GeneralCollectionResult;

public class UserDetailActivity extends BaseActivity {
	private static final int GET_WISH_SUCCESS = 0;
	private static final int GET_READ_SUCCESS = 1;
	private static final int GET_READING_SUCCESS = 2;
	private static final int GET_FAILURE = 3;

	GeneralCollectionResult wishResult;
	GeneralCollectionResult readResult;
	GeneralCollectionResult readingResult;

	AuthorUser userDetail;
	TextView readerIntro_tv;
	LinearLayout reading_lyt, readingList_lyt;
	LinearLayout read_lyt, readList_lyt;
	LinearLayout wish_lyt, wishList_lyt;
	DoubanBusiness doubanBusiness = new DoubanBusiness(this);
	// thread
	private MessageHandler msgHandler;

	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case GET_WISH_SUCCESS:
				setWidgetsAndListener(msg, wish_lyt, wishList_lyt);
				break;
			case GET_READ_SUCCESS:
				setWidgetsAndListener(msg, read_lyt, readList_lyt);
				break;
			case GET_READING_SUCCESS:
				setWidgetsAndListener(msg, reading_lyt, reading_lyt);
				break;
			case GET_FAILURE:
				// TODO charge code
				toast(((WrongMsg) msg.obj).getMsg());
				break;
			default:
				break;
			}
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

	void setWidgetsAndListener(Message msg, LinearLayout lyt,
			LinearLayout list_lyt) {
		List<CollectionItem> alls = ((GeneralCollectionResult) msg.obj)
				.getCollections();
		int k = alls == null ? 0 : alls.size();
		if (k == 0) {
			return;
		} else {
			for (final CollectionItem au : alls) {
				ImageView iv = new ImageView(context);
				ImageDownloader imgdown = new ImageDownloader(context);
				imgdown.download(au.getBook().getImage(), iv, null);
				list_lyt.addView(iv);
				TextView margin = new TextView(context);
				margin.setText("3dp");
				margin.setVisibility(View.INVISIBLE);
				list_lyt.addView(margin);
				iv.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						//TODO reget bookData by API GET  https://api.douban.com/v2/book/:id
						Intent intent = new Intent(context,
								BookDetailActivity.class);
						intent.putExtra("bookItem", au.getBook());
						startActivity(intent);
					}
				});
			}
			lyt.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.a_user_detail);
		findViews();
		initDatas();
		initWidgets();
		getData();
	}

	void getData() {

		doubanBusiness.getUserCollections(String.valueOf(userDetail.getId()),
				"wish", new AsynCallback<GeneralCollectionResult>() {
					@Override
					public void onSuccess(GeneralCollectionResult data) {
						super.onSuccess(data);
					}

					@Override
					public void onFailure(WrongMsg caught) {
						super.onFailure(caught);
						sendMessage(caught, GET_FAILURE);
					}
				});
		doubanBusiness.getUserCollections(String.valueOf(userDetail.getId()),
				"read", new AsynCallback<GeneralCollectionResult>() {
					@Override
					public void onSuccess(GeneralCollectionResult data) {
						super.onSuccess(data);
						sendMessage(data, GET_READ_SUCCESS);
					}

					@Override
					public void onFailure(WrongMsg caught) {
						super.onFailure(caught);
						sendMessage(caught, GET_FAILURE);
					}
				});
		doubanBusiness.getUserCollections(String.valueOf(userDetail.getId()),
				"wish", new AsynCallback<GeneralCollectionResult>() {
					@Override
					public void onSuccess(GeneralCollectionResult data) {
						super.onSuccess(data);
						sendMessage(data, GET_WISH_SUCCESS);
					}

					@Override
					public void onFailure(WrongMsg caught) {
						super.onFailure(caught);
						sendMessage(caught, GET_FAILURE);
					}
				});

	}

	@Override
	void findViews() {
		super.findViews();
		readerIntro_tv = (TextView) findViewById(R.id.readerIntro_tv);

		wish_lyt = (LinearLayout) this.findViewById(R.id.wish_lyt);
		wishList_lyt = (LinearLayout) this.findViewById(R.id.wishList_lyt);

		read_lyt = (LinearLayout) this.findViewById(R.id.read_lyt);
		readList_lyt = (LinearLayout) this.findViewById(R.id.readList_lyt);

		reading_lyt = (LinearLayout) this.findViewById(R.id.reading_lyt);
		readingList_lyt = (LinearLayout) this
				.findViewById(R.id.readingList_lyt);
	}

	@Override
	void initDatas() {
		super.initDatas();
		Looper looper = Looper.myLooper();
		msgHandler = new MessageHandler(looper);
		userDetail = (AuthorUser) getIntent()
				.getSerializableExtra("userDetail");
		context = this;
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		if (notNull(userDetail.getDesc().trim()))
			readerIntro_tv.setText(userDetail.getDesc());
		else
			readerIntro_tv.setText("  还没有个人介绍");
		wish_lyt.setVisibility(View.GONE);
		read_lyt.setVisibility(View.GONE);
		reading_lyt.setVisibility(View.GONE);
	}

}
