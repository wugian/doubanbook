package com.study.doubanbook_for_android.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.auth.AccessToken;
import com.study.doubanbook_for_android.auth.KeepToken;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.AuthorUser;
import com.study.doubanbook_for_android.utils.DebugUtils;
import com.study.doubanbook_for_android.utils.ShowErrorUtils;

/**
 * TODO 13-12-24 在初始页面结束时,清除所有XML的TOKEN,是否有必要清除WEBVIEW的授权凭证
 * 
 * @author tezuka-pc
 * 
 */
public class SerchInputActivity extends BaseActivity {

	EditText search_et;
	Button search_btn;
	TextView clear_tv;
	private Context context;
	private Button authBtn;
	private TextView readerSearch;
	private TextView bookSearch;
	private static final int SEARCH_BOOK = 0;// search book flag
	private static final int SEARCH_READER = 1;// search reader flag
	private static final int GET_USERDETAIL_SUCCESS = 2;
	private static final int GET_USERDETAIL_FAILURE = 3;

	DoubanBusiness doubanBusiness = new DoubanBusiness(this);

	private int flag = SEARCH_BOOK;// search flag

	// thread
	private MessageHandler msgHandler;

	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case GET_USERDETAIL_SUCCESS:
				AuthorUser ud = (AuthorUser) msg.obj;
				Intent intent = new Intent(context, UserDetailActivity.class);
				AccessToken ac = KeepToken.readAccessToken(context);
				intent.putExtra("userDetail", ud);
				startActivity(intent);
				break;
			case GET_USERDETAIL_FAILURE:
				ShowErrorUtils.showWrongMsg(context, msg);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_serch_input);
		DebugUtils.e("CLASS", getClass().getName());
		Looper looper = Looper.myLooper();
		msgHandler = new MessageHandler(looper);

		context = this;
		findViews();
		initWidgets();
		initListners();
		// auto auth
		doubanBusiness.auth();
		search_et.setText("求魔");
		
		doubanBusiness.getCommentList("9863114952", new AsynCallback<String>(){
			@Override
			public void onSuccess(String data) {
				super.onSuccess(data);
				System.out.println(data);
			}
			@Override
			public void onFailure(WrongMsg caught) {
				super.onFailure(caught);
				System.out.println(caught.getMessage());
			}
		});

	}

	@Override
	void findViews() {
		super.findViews();
		authBtn = (Button) this.findViewById(R.id.authBtn);
		search_et = (EditText) this.findViewById(R.id.serchContent_et);
		search_btn = (Button) this.findViewById(R.id.search_btn);
		clear_tv = (TextView) this.findViewById(R.id.clear_tv);
		authBtn = (Button) this.findViewById(R.id.authBtn);
		bookSearch = (TextView) this.findViewById(R.id.bookSearch_tv);
		readerSearch = (TextView) this.findViewById(R.id.readerSearch_tv);
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		clear_tv.setVisibility(View.GONE);
		// bookSearch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		readerSearch.setTextColor(Color.GRAY);
	}

	@Override
	void initListners() {
		super.initListners();

		search_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println(flag);
				if (flag == SEARCH_BOOK) {
					Intent intent = new Intent();
					intent.setClass(context, BookListsActivity.class);
					String s = getText(search_et);
					intent.putExtra("searchContent", s);
					startActivity(intent);
				} else if (flag == SEARCH_READER) {
					Intent intent = new Intent();
					intent.setClass(context, UserListActivity.class);
					String s = getText(search_et);
					intent.putExtra("searchContent", s);
					startActivity(intent);
				}
			}
		});
		search_et.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = getText(search_et);
				if (!str.toString().equals("")) {
					clear_tv.setVisibility(View.VISIBLE);
				} else {
					clear_tv.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		clear_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				search_et.setText("");
			}
		});

		bookSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// bookSearch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//
				// 下划线
				// readerSearch.getPaint().setFlags(Paint.););
				bookSearch.setTextColor(Color.BLACK);
				readerSearch.setTextColor(Color.GRAY);
				search_et.setHint(getResources().getString(
						R.string.search_book_hint));
				flag = SEARCH_BOOK;
				System.out.println(flag);
			}
		});

		readerSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// readerSearch.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//
				// 下划线
				// readerSearch.getPaint().setFlags(Paint.););
				bookSearch.setTextColor(Color.GRAY);
				readerSearch.setTextColor(Color.BLACK);
				search_et.setHint(getResources().getString(
						R.string.search_reader_hint));
				flag = SEARCH_READER;
				System.out.println(flag);
			}
		});
		authBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AccessToken ac = KeepToken.readAccessToken(context);
				if (notNull(ac.getDoubanUserId())) {
					doubanBusiness.getUserDetail(ac.getDoubanUserId(),
							new AsynCallback<AuthorUser>() {
								public void onSuccess(AuthorUser data) {
									sendMessage(data, GET_USERDETAIL_SUCCESS);
								};

								public void onFailure(WrongMsg caught) {
									sendMessage(caught, GET_USERDETAIL_FAILURE);
								};
							});
				} else {
					toast("请先进行登录授权");
					doubanBusiness.auth();
				}
			}
		});
	}

	long exitTime = 0;

	// 线程安全
	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			toast("再按一次退出");
			exitTime = System.currentTimeMillis();
		} else {
			super.onBackPressed();
			KeepToken.clear(context);
		}
	}
}
