package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.business.DoubanBusiness;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.BookItem;
import com.study.doubanbook_for_android.model.CollectBookMsg;
import com.study.doubanbook_for_android.model.CollectSuccessResult;

public class BookDetailActivity extends BaseActivity {
	ImageView bookImg;
	TextView authorSumary_tv;
	TextView bookSumary;
	BookItem bookItem = null;
	TextView title;
	TextView author;
	TextView price;
	TextView publisher;
	TextView grade;
	Button comment;
	private String bookid;
	private Button wish;
	private Button reading;
	private Button done;
	private PopupWindow popwindow;
	private LinearLayout bookDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_book_detail);
		findViews();
		initDatas();
		initWidgets();
		initListners();
		initPopWindow();
	}

	@SuppressWarnings("deprecation")
	private void initPopWindow() {
		LayoutInflater factory = LayoutInflater.from(this);
		View view = factory.inflate(R.layout.a_collect_detail, null);

		popwindow = new PopupWindow(view,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT);

		final EditText tag_et = (EditText) view.findViewById(R.id.tag_et);
		final RatingBar ratingBar_rb = (RatingBar) view
				.findViewById(R.id.ratingBar_rb);
		final EditText comment_et = (EditText) view
				.findViewById(R.id.comment_et);
		Button cancle_btn = (Button) view.findViewById(R.id.cancle_btn);
		Button ok_btn = (Button) view.findViewById(R.id.ok_btn);

		ok_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int rating = (int) ratingBar_rb.getRating();
				String s = getText(tag_et);
				String comment = getText(comment_et);
				System.out.println(rating + " ," + s + " ," + comment);
				dissmissPop();
				collectBook(rating, s, comment);
			}
		});
		cancle_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dissmissPop();
			}
		});
		/* 点其他地方可消失 */
		popwindow.setFocusable(true);
		popwindow.setBackgroundDrawable(new BitmapDrawable());
	}

	protected void collectBook(int rating, String title, String comment) {
		CollectBookMsg collectBookMsg = new CollectBookMsg();
		collectBookMsg.setStatus("done");
		if (notZero(rating))
			collectBookMsg.setRating(rating);
		if (notNull(title))
			collectBookMsg.setTags(title);
		if (notNull(comment))
			collectBookMsg.setComment(comment);
		DoubanBusiness doubanBusiness = new DoubanBusiness(this);
		doubanBusiness.collectBook(bookid, collectBookMsg,
				new AsynCallback<CollectSuccessResult>() {
					@Override
					public void onSuccess(CollectSuccessResult data) {
						super.onSuccess(data);
						System.out.println(data.toString());
					}

					@Override
					public void onFailure(WrongMsg caught) {
						super.onFailure(caught);
						System.out.println(caught.toString());
					}
				});

	}

	private void dissmissPop() {
		if (popwindow.isShowing())
			popwindow.dismiss();
	}

	private void showPop() {
		if (popwindow != null) {
			popwindow.showAtLocation(bookDetail, Gravity.CENTER_VERTICAL
					| Gravity.CENTER_HORIZONTAL, 0, 0);
		}
	}

	@Override
	void findViews() {
		super.findViews();
		bookDetail = (LinearLayout) findViewById(R.id.bookDetail_lyt);
		bookImg = (ImageView) findViewById(R.id.bookImg_iv);
		authorSumary_tv = (TextView) findViewById(R.id.authorSumary_tv);
		bookSumary = (TextView) findViewById(R.id.bookSumary_tv);
		title = (TextView) findViewById(R.id.bookName_tv);
		author = (TextView) findViewById(R.id.bookAuthor_tv);
		price = (TextView) findViewById(R.id.bookPrice_tv);
		publisher = (TextView) findViewById(R.id.bookPublisher_tv);
		grade = (TextView) findViewById(R.id.bookGrade_tv);
		comment = (Button) findViewById(R.id.comment_btn);
		wish = (Button) findViewById(R.id.wish_btn);
		reading = (Button) findViewById(R.id.reading_btn);
		done = (Button) findViewById(R.id.read_btn);
	}

	@Override
	void initDatas() {
		super.initDatas();
		bookItem = (BookItem) getIntent().getSerializableExtra("bookItem");
		if (bookItem == null) {
			logD("TTT", "BOOKITEM IS NULL");
		}
		bookid = String.valueOf(bookItem.getId());
	}

	@Override
	void initWidgets() {
		super.initWidgets();
		imageDownloader.download(bookItem.getImages().getMedium(), bookImg,
				null);
		authorSumary_tv.setText("作者简介:" + bookItem.getAuthor_intro());
		bookSumary.setText("图书简介:" + bookItem.getSummary());

		title.setText(bookItem.getTitle());
		price.setText(bookItem.getPrice());
		publisher
				.setText(bookItem.getPublisher() + " " + bookItem.getPubdate());
		if (bookItem.getRating() != null)
			grade.setText(bookItem.getRating().getAverage() + "分 "
					+ bookItem.getRating().getNumRaters() + "人已评论");
		StringBuffer stringBuffer = new StringBuffer();
		for (String s : bookItem.getAuthor()) {
			stringBuffer.append(s);
			stringBuffer.append(" ");

		}
		author.setText(stringBuffer.toString());

		if (bookItem.getCurrent_user_collection() == null) {
			resetTextColor(wish, reading, done);
			wish.setTextColor(Color.GRAY);
		} else {
			String status = bookItem.getCurrent_user_collection().getStatus();
			// 想读：wish 在读：reading 或 doing 读过：read 或 done）
			if (status.equals("wish")) {
				resetTextColor(wish, reading, done);
			} else if (status.equals("reading") || status.equals("doing")) {
				resetTextColor(reading, wish, done);
			} else if (status.equals("done") || status.equals("read")) {
				resetTextColor(done, wish, reading);
			}
		}
	}

	// 将其他两个文字颜色为灰色
	void resetTextColor(Button btn1, Button btn2, Button btn3) {
		btn1.setTextColor(Color.BLACK);
		btn2.setTextColor(Color.GRAY);
		btn3.setTextColor(Color.GRAY);
	}

	@Override
	void initListners() {
		super.initListners();
		// 显示评论
		comment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, BookNoteListActivity.class);
				intent.putExtra("bookid", bookid);
				startActivity(intent);
			}
		});
		wish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 想读：wish 在读：reading 或 doing 读过：read 或 done）
				resetTextColor(wish, reading, done);
				showPop();

			}
		});
	}
}
