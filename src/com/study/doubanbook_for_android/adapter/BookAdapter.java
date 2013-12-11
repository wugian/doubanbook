package com.study.doubanbook_for_android.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.imagedownloader.ImageDownloader;
import com.study.doubanbook_for_android.model.BookItem;

public class BookAdapter extends BaseAdapter {

	private ArrayList<BookItem> books;
	private Context context;

	public BookAdapter(ArrayList<BookItem> books, Context context) {
		super();
		this.books = books;
		this.context = context;
	}

	@Override
	public int getCount() {
		return books.size();
		// return 5;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageDownloader imgDownloader = new ImageDownloader(context);
		BookItem item = books.get(position);
		ViewHolder holder = new ViewHolder();

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.i_bookserchlist, null);
			holder.img = (ImageView) convertView.findViewById(R.id.bookImg_iv);
			holder.title = (TextView) convertView
					.findViewById(R.id.bookName_tv);
			holder.author = (TextView) convertView
					.findViewById(R.id.bookAuthor_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		/* 设置 */
		holder.title.setText(item.getTitle());
		// TODO GET THE image's size
		imgDownloader.download(item.getImage(), holder.img, null);
		StringBuffer stringBuffer = new StringBuffer();
		for (String s : item.getAuthor()) {
			stringBuffer.append(s);
			stringBuffer.append(" ");

		}
		holder.author.setText(stringBuffer.toString());
		holder.author.setTextColor(Color.BLUE);
		return convertView;
	}

	static class ViewHolder {
		ImageView img;
		TextView title;
		TextView author;
	}
}
