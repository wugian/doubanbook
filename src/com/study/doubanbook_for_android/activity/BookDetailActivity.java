package com.study.doubanbook_for_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.model.BookItem;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_book_detail);
		findViews();
		initDatas();
		initWidgets();
		initWidgets();
		initListners();
	}

	@Override
	void findViews() {
		super.findViews();
		bookImg = (ImageView) findViewById(R.id.bookImg_iv);
		authorSumary_tv = (TextView) findViewById(R.id.authorSumary_tv);
		bookSumary = (TextView) findViewById(R.id.bookSumary_tv);
		title = (TextView) findViewById(R.id.bookName_tv);
		author = (TextView) findViewById(R.id.bookAuthor_tv);
		price = (TextView) findViewById(R.id.bookPrice_tv);
		publisher = (TextView) findViewById(R.id.bookPublisher_tv);
		grade = (TextView) findViewById(R.id.bookGrade_tv);
		comment = (Button) findViewById(R.id.comment_btn);
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
	}

	@Override
	void initListners() {
		super.initListners();
		comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, BookNoteActivity.class);
				intent.putExtra("bookid", bookid);
				startActivity(intent);
			}
		});
	}
}
