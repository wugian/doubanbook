package com.study.doubanbook_for_android.activity;

import android.app.Activity;

/**
 * 用以记录后期任务
 * @author tezuka-pc
 *
 */
public class TASK_TODO extends Activity {
	/**
	 *TODO 对图书链接可以生成一个二维码图片方便用户#
	 *TODO 在退出时要清除系统的所有记录,主要是WEBVIEW#
	 *TODO 适当修改UI,UE#
	 *TODO 修改NETUTILS 的其他工具类尽量重新封装,以作后用#
	 * 由于时间关系最近不会再对此做修改,会尽量抽时间来完善这个应用,添加电影...
	 */
	// private Button testbtn;
	// private Button postTest;
	// Context context;
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_loading_page);
	// postTest = (Button) findViewById(R.id.postTest);
	//
	// context = this;
	// testbtn = (Button) findViewById(R.id.testbtn);
	// testbtn.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// Douban douban = Douban.getInstance();
	// douban.authorize(TASK_CLASS_TODO.this,
	// new SimpleDoubanOAuthListener());
	// }
	// });
	//
	// // after auth
	// postTest.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// new Thread() {
	// public void run() {
	// NetUtils.getHttpEntity(
	// "https://api.douban.com/v2/book/72719211/collection",
	// NetUtils.POST, null, null, context);
	// };
	// }.start();
	//
	// }
	// });
	// }
}
