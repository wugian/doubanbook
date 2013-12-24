package com.study.doubanbook_for_android.business;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.activity.BaseActivity;
import com.study.doubanbook_for_android.api.NetUtils;
import com.study.doubanbook_for_android.api.WrongMsg;
import com.study.doubanbook_for_android.auth.Douban;
import com.study.doubanbook_for_android.auth.SimpleDoubanOAuthListener;
import com.study.doubanbook_for_android.callback.AsynCallback;
import com.study.doubanbook_for_android.model.CollectBookMsg;
import com.study.doubanbook_for_android.model.CollectSuccessResult;
import com.study.doubanbook_for_android.model.GeneralNoteResult;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.model.GeneralUserResult;
import com.study.doubanbook_for_android.model.URLMananeger;


public class DoubanBusiness {
	
	private Context context;
	
	
	public DoubanBusiness(Context context) {
		super();
		this.context = context;
	}
	
	public void auth(){
		Douban douban = Douban.getInstance();
		douban.authorize(context, new SimpleDoubanOAuthListener());
	}

	// TODO 修改整个方法,利用线程,回调,得到 DONE
	// 得到字符串,转化成MODEL时没有TRY CATCH,怎样判断是否是错误信息,先变成WRONGMSG
	// MODEL?判断CODE?添加TAG,CODE==0转化成对应模型否则转化成WRONGMSG
	/**
	 * 	q 		查询关键字 q和tag必传其一
	 *  tag 	查询的tag q和tag必传其一 
	 *  start 	取结果的offset 默认为0 
	 *  count 	取结果的条数 * 默认为20，最大为100
	 * 
	 * @param q
	 * @param start
	 * @param callback
	 */
	public void getSearchList(final String q, final int start,
			final AsynCallback<GeneralResult> callback) {
		new Thread() {
			public void run() {
				WrongMsg wrongMsg = new WrongMsg();
				GeneralResult result = null;
				Gson gson = new Gson();
				String s = "";
				List<String> keys = new ArrayList<String>();
				List<String> values = new ArrayList<String>();

				// init keys
				keys.add("q");
				keys.add("start");
				keys.add("count");
				// init values
				try {
					// change the encode when necessary
					values.add(URLEncoder.encode(q, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				values.add(start + "");
				values.add(BaseActivity.PAGE_COUNT + "");

				callback.onStart();
				s = NetUtils.getHttpEntity(
						getBasicUrl(URLMananeger.BOOK_WRITER_SEARCHR_URL),
						NetUtils.GET, keys, values, context);
				wrongMsg = gson.fromJson(s, new TypeToken<WrongMsg>() {
				}.getType());
				if (wrongMsg.getCode() != 0) {
					Log.d("NET", "wrongmsg model");
					callback.onFailure(wrongMsg);
				} else {
					Log.d("NET", "right model");
					result = gson.fromJson(s, new TypeToken<GeneralResult>() {
					}.getType());
					callback.onSuccess(result);
				}
				callback.onDone();
			};
		}.start();
	}

	/**
	 * format 	返回content字段格式 	选填（编辑伪标签格式：text, HTML格式：html），默认为text 
	 * order 	排序 * 				选填（最新笔记：collect, 按有用程度：rank, 按页码先后：page），默认为rank 
	 * page 	按页码过滤				 选填
	 * 
	 * @param bookid
	 * @param start
	 * @param callback
	 */
	public void getNoteList(final String bookid, final int start,
			final AsynCallback<GeneralNoteResult> callback) {
		new Thread() {
			public void run() {
				WrongMsg wrongMsg = new WrongMsg();
				GeneralNoteResult result = null;
				Gson gson = new Gson();
				String s = "";
				List<String> keys = new ArrayList<String>();
				List<String> values = new ArrayList<String>();

				// init keys
				keys.add("start");
				keys.add("count");
				// init values
				values.add(start + "");
				values.add(BaseActivity.PAGE_COUNT + "");
				callback.onStart();
				s = NetUtils.getHttpEntity(
						getBasicUrl(URLMananeger.BOOK_NOTE_LIST_URL.replace(
								":id", bookid)), NetUtils.GET, keys, values,
						null);
				wrongMsg = gson.fromJson(s, new TypeToken<WrongMsg>() {
				}.getType());
				if (wrongMsg.getCode() != 0) {
					Log.d("NET", "wrongmsg model");
					callback.onFailure(wrongMsg);
				} else {
					Log.d("NET", "right model");
					result = gson.fromJson(s,
							new TypeToken<GeneralNoteResult>() {
							}.getType());
					callback.onSuccess(result);
				}
				callback.onDone();
			};
		}.start();
	}
 
	/**
	 * 
	 * 用户收藏图书或者修改收藏状态
	 * 
	 *  POST  https://api.douban.com/v2/book/:id/collection
		参数		意义			备注
		status	收藏状态		必填（想读：wish 在读：reading 或 doing 读过：read 或 done）
		tags	收藏标签字符串	选填，用空格分隔
		comment	短评文本		选填，最多350字
		privacy	隐私设置		选填，值为'private'为设置成仅自己可见，其他默认为公开
		rating	星评			选填，数字1～5为合法值，其他信息默认为不评星
	 * @param bookid
	 * @param start
	 * @param callback
	 */
	//TODO NEED TEST when success show toast
	public void collectBook(final String bookid,final CollectBookMsg collectmsg,
			final AsynCallback<CollectSuccessResult> callback) {
		new Thread() {
			public void run() {
				WrongMsg wrongMsg = new WrongMsg();
				CollectSuccessResult result = null;
				Gson gson = new Gson();
				String s = "";
				List<String> keys = new ArrayList<String>();
				List<String> values = new ArrayList<String>();

				keys.add("status");//NOT NULL
				values.add(collectmsg.getStatus());
				//TODO NOT USE YET
				//keys.add("tags");
				if(collectmsg.getComment()!=null){
					values.add(collectmsg.getComment());
					keys.add("comment");
				}
				if(collectmsg.getPrivacy()){
					keys.add("privacy");
					values.add("private");
				}
				if(collectmsg.getRating()>=1&&collectmsg.getRating()<=5){
					keys.add("rating");
					values.add(String.valueOf(collectmsg.getRating()));
				}
				 
				callback.onStart();
				
				s = NetUtils.getHttpEntity(
						getBasicUrl(URLMananeger.BOOK_COLLECT_URL.replace(
								":id", bookid)), NetUtils.POST, keys, values,
						context);
				wrongMsg = gson.fromJson(s, new TypeToken<WrongMsg>() {
				}.getType());
				if (wrongMsg.getCode() != 0) {
					Log.d("NET", "wrongmsg model");
					callback.onFailure(wrongMsg);
				} else {
					Log.d("NET", "right model");
					result = gson.fromJson(s,
							new TypeToken<CollectSuccessResult>() {
							}.getType());
					callback.onSuccess(result);
				}
				callback.onDone();
			};
		}.start();
	}
	
	/**
	 * 
		GET  https://api.douban.com/v2/book/user/:name/collections
		参数			意义						备注
		status		收藏状态					选填（想读：wish 在读：reading 读过：read）默认为所有状态
		tag			收藏标签					选填
		from		按收藏更新时间过滤的起始时间	选填，格式为符合rfc3339的字符串，例如"2012-10-19T17:14:11"，其他信息默认为不传此项
		to			按收藏更新时间过滤的结束时间	同上
		rating	星评	选填，数字1～5为合法值，其他信息默认为不区分星评
	 * @param bookid
	 * @param collectmsg
	 * @param start
	 * @param callback
	 */
	public void getUserCollection(final String bookid,final CollectBookMsg collectmsg, final int start,
			final AsynCallback<CollectSuccessResult> callback) {
		new Thread() {
			public void run() {
				WrongMsg wrongMsg = new WrongMsg();
				CollectSuccessResult result = null;
				Gson gson = new Gson();
				String s = "";
				List<String> keys = new ArrayList<String>();
				List<String> values = new ArrayList<String>();

				// init keys
				// init values
				keys.add("status");
				values.add(collectmsg.getStatus());
				//TODO NOT USE YET
				//keys.add("tags");
				if(collectmsg.getComment()!=null){
					values.add(collectmsg.getComment());
					keys.add("comment");
				}
				if(collectmsg.getPrivacy()){
					keys.add("privacy");
					values.add("private");
				}
				if(collectmsg.getRating()>=1&&collectmsg.getRating()<=5){
					keys.add("rating");
					values.add(String.valueOf(collectmsg.getRating()));
				}
				 
				callback.onStart();
				
				s = NetUtils.getHttpEntity(
						getBasicUrl(URLMananeger.BOOK_COLLECT_URL.replace(
								":name", bookid)), NetUtils.POST, keys, values,
						context);
				wrongMsg = gson.fromJson(s, new TypeToken<WrongMsg>() {
				}.getType());
				if (wrongMsg.getCode() != 0) {
					Log.d("NET", "wrongmsg model");
					callback.onFailure(wrongMsg);
				} else {
					Log.d("NET", "right model");
					result = gson.fromJson(s,
							new TypeToken<CollectSuccessResult>() {
							}.getType());
					callback.onSuccess(result);
				}
				callback.onDone();
			};
		}.start();
	}
	
	
	
	
	//TODO NOT FIND TEST BY POSTMAN DONE BY 2013-12-20-20:39
	/**
	 * GET  https://api.douban.com/v2/user
		参数	意义	备注
		q	查询关键字	q和tag必传其一
		tag	查询的tag	q和tag必传其一
		start	取结果的offset	默认为0
		count	取结果的条数	默认为20，最大为100
 	
	 * @param userName
	 * @param collectmsg
	 * @param start
	 * @param callback
	 */
	
	public void searchUser(final String userName,final int start,
			final AsynCallback<GeneralUserResult> callback) {
		new Thread() {
			public void run() {
				WrongMsg wrongMsg = new WrongMsg();
				GeneralUserResult result = null;
				Gson gson = new Gson();
				String s = "";
				List<String> keys = new ArrayList<String>();
				List<String> values = new ArrayList<String>();
				// init keys
				keys.add("q");
				keys.add("start");
				keys.add("count");
				// init values
				try {
					// change the encode when necessary
					values.add(URLEncoder.encode(userName, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				values.add(start + "");
				values.add(BaseActivity.PAGE_COUNT + "");

				callback.onStart();
				
				s = NetUtils.getHttpEntity(
						getBasicUrl(URLMananeger.USER_SEARCH_URL),
						NetUtils.GET, keys, values, null);
				
				wrongMsg = gson.fromJson(s, new TypeToken<WrongMsg>() {
				}.getType());
				if (wrongMsg.getCode() != 0) {
					Log.d("NET", "wrongmsg model");
					callback.onFailure(wrongMsg);
				} else {
					Log.d("NET", "right model");
					result = gson.fromJson(s, new TypeToken<GeneralUserResult>() {
					}.getType());
					
					callback.onSuccess(result);
				}
				
				callback.onDone();
			};
		}.start();
	}
	/**
	 * get request url
	 * 
	 * @param url
	 * @return
	 */
	private static String getBasicUrl(String url) {
		return URLMananeger.ROOT_ULR + url;
	}

}
