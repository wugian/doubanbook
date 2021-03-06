package com.study.doubanbook_for_android.activity;

import android.app.Activity;
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
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.auth.AccessToken;
import com.study.doubanbook_for_android.auth.Douban;
import com.study.doubanbook_for_android.auth.DoubanDialogError;
import com.study.doubanbook_for_android.auth.KeepToken;
import com.study.doubanbook_for_android.auth.SimpleDoubanOAuthListener;
import com.study.doubanbook_for_android.auth.Token;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.AuthorUser;
import com.study.doubanbook_for_android.utils.DebugUtils;
import com.study.doubanbook_for_android.utils.PrefUtils;
import com.study.doubanbook_for_android.utils.ToastUtils;

/**
 * 主搜索页面
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
	private static final int AUTHOR_SUCCESS = 4;
	private static final int AUTHOR_FAILURE = 5;

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
				intent.putExtra("userDetail", ud);
				startActivity(intent);
				break;
			case AUTHOR_SUCCESS:
				Token token = (Token) msg.obj;
				ToastUtils.toast(context, token.douban_user_name + "登录成功");
				authBtn.setText(token.douban_user_name);
				break;
			case AUTHOR_FAILURE:
				DoubanDialogError e2 = (DoubanDialogError) msg.obj;
				ToastUtils.toast(context, e2.getMessage());
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
		setNavagator("搜索主页", R.drawable.ic_setting);
		back.setVisibility(View.INVISIBLE);
		// auto auth
		if (PrefUtils.getAutoLogin(context)) {
			auth();
		}
		// this just test to delete
		// search_et.setText("求魔");
	}

	@Override
	void initRightListener() {
		super.initRightListener();
		Intent intent = new Intent(this, SettingActivity.class);
		startActivityForResult(intent, BaseP2RActivity.REQUEST_CODE_CHANGED);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK
				&& requestCode == BaseP2RActivity.REQUEST_CODE_CHANGED)
			if (data.getBooleanExtra("isChanged", false)) {
				finish();
			}
	}

	void auth() {
		Douban douban = Douban.getInstance();
		douban.authorize(context, new SimpleDoubanOAuthListener() {
			@Override
			public void onComplete(Token token) {
				super.onComplete(token);
				sendMessage(token, AUTHOR_SUCCESS);
			}

			@Override
			public void onError(DoubanDialogError e) {
				super.onError(e);
				sendMessage(e, AUTHOR_FAILURE);
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
		// 这个是设置需要旋转的角度，我设置的是180度
		RotateAnimation rotateAnimation = new RotateAnimation(0, 90, 40, 0);
		// 这个是设置通话时间的
		rotateAnimation.setDuration(10);
		rotateAnimation.setFillAfter(true);
		authBtn.startAnimation(rotateAnimation);
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
					auth();
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
