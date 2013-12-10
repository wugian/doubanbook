package com.study.doubanbook_for_android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.model.BookItem;

public class BookAdapter extends BaseAdapter {

	private List<BookItem> books;
	private Context context;

	public BookAdapter(List<BookItem> books, Context context) {
		super();
		this.books = books;
		this.context = context;
	}

	@Override
	public int getCount() {
		// return books.size();
		return 5;
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

		// ImageDownloader imgDownloader = new ImageDownloader(context);
		BookItem item = books.get(position);
		ViewHolder holder = new ViewHolder();

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.i_bookserchlist, null);
			holder.img = (ImageView) convertView.findViewById(R.id.bookImg_iv);
			holder.title = (TextView) convertView
					.findViewById(R.id.bookName_tv);
			holder.author = (LinearLayout) convertView
					.findViewById(R.id.bookAuthor_lyt);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		/* 设置 */
		// holder.name.setText(item.getOther_name());
		// holder.msg.setText(item.getMsg());
		// holder.unread.setText(item.getUnread_count() + "");

		// try {
		// imgDownloader.download(infoListItem.getOther_logo(),
		// holder.img, null);
		// } catch (Exception e) {
		// //
		// }
		return convertView;
	}

	static class ViewHolder {
		ImageView img;
		TextView title;
		LinearLayout author;
	}
}
