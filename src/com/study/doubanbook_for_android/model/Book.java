package com.study.doubanbook_for_android.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Book {
	private String start;
	private int count;
	private int total;
	@SerializedName("books")
	private List<Book> bookItems;// : [Book, ]

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Book> getBookItems() {
		return bookItems;
	}

	public void setBookItems(List<Book> bookItems) {
		this.bookItems = bookItems;
	}

	@Override
	public String toString() {
		return "Book [start=" + start + ", count=" + count + ", total=" + total
				+ ", bookItems=" + bookItems + "]";
	}

}
