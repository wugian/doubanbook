package com.study.doubanbook_for_android.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.doubanbook_for_android.R;
import com.study.doubanbook_for_android.R.id;
import com.study.doubanbook_for_android.api.NetUtils;
import com.study.doubanbook_for_android.business.LoginBusiness;
import com.study.doubanbook_for_android.model.AcToken;

public class TestActivity extends BaseActivity {

	String testUrl = "https://api.douban.com/v2/book/72719211/collection";
	// thread
	private MessageHandler msgHandler;

	class MessageHandler extends Handler {
		public MessageHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			// 处理收到的消 息，把天气信息显示在title上
			AcToken result = (AcToken) (msg.obj);
			Log.d("NET", result.toString());
			System.out.println();
			testPost(result.getAccess_token());

		}
	}

	/**
	 * 利用MESSAGEHANDLER发送消息到UI线程
	 * 
	 * @param b
	 * @param status
	 */
	void sendMessage(Object b) {
		Message message = Message.obtain();
		message.obj = b;
		msgHandler.sendMessage(message);
	}

	public void testPost(final String string) {
		new Thread() {
			public void run() {
				System.out.println(NetUtils.getHttpPost(testUrl, null, null,
						string));// (testUrl
				/* + "&access_token=" + string */// , NetUtils.POST,
				// null, null));
			};
		}.start();

	}

	WebView webView;
	private String url1 = "https://www.douban.com/service/auth2/auth";
	private String client_id = "09561172f001e8251c023458d005b86f";// 必选参数，应用的唯一标识，对应于APIKey
	private String redirect_uri = "http://sns.whalecloud.com/douban/callback";// 用户授权完成后的回调地址，应用需要通过此回调地址获得用户的授权结果。
	private String response_type = "code";// 必选参数，此值可以为 code 或者 token 。在本流程中，此值为

	// code
	private String scope;// 可选参数，申请权限的范围，如果不填，则使用缺省的scope。如果申请多个scope，使用逗号分隔。
	private String state;// 可选参数，用来维护请求和回调状态的附加字符串，在授权完成回调时会附加此参数，应用可以根据此字符串来判断上下文关系。
	private String client_secret = "f31eaf3a7bf0a4cf";// 必选参数，应用的唯一标识，对应于豆瓣secret
	private String grant_type = "authorization_code";// 必选参数，此值可以为
														// authorization_code 或者
														// refresh_token
	// 。在本流程中，此值为 authorization_code
	private String code;// 必选参数，上一步中获得的authorization_code

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading_page);

		// init msgHandler
		Looper looper = Looper.myLooper();
		msgHandler = new MessageHandler(looper);

		webView = (WebView) findViewById(id.webView);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true); // Soutenir Javascript
		webSettings.setAllowFileAccess(true); // Autoriser WebView visiter des
		// Files
		webSettings.setBuiltInZoomControls(true);
		webView.setWebViewClient(new DoubanWebViewClient() {
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed();
			}
		});

		webView.loadUrl("https://www.douban.com/service/auth2/auth?client_id=09561172f001e8251c023458d005b86f&redirect_uri=http://sns.whalecloud.com/douban/callback&response_type=code");

		// webView.
		final LoginBusiness lb = new LoginBusiness();
		// new Thread() {
		// public void run() {
		// String s = lb.getAutorizationCode();
		// System.out.println(s);
		// };
		// }.start();
	}

	String codeIndex = "code=";

	private class DoubanWebViewClient extends WebViewClient {

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		/**
		 * 1,get code(auth code)
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			System.out.println(url);
			logD("TTT", url);
			if (url.contains(codeIndex)) {
				code = url.substring(url.indexOf(codeIndex)
						+ codeIndex.length());
				handleAuthCode(code);
			} else
				Toast.makeText(getApplicationContext(), "wrong msg",
						Toast.LENGTH_SHORT).show();
		}

	}

	// ------other basic method

	public String getAutorizationUrl(String urls) {
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add("client_id");
		keys.add("redirect_uri");
		keys.add("response_type");
		values.add(client_id);
		values.add(redirect_uri);
		values.add(response_type);
		String s = getUrlStr(urls, values, keys);
		return s;
	}

	private String url2 = "https://www.douban.com/service/auth2/token";
	List<String> keys1 = new ArrayList<String>();
	List<String> values1 = new ArrayList<String>();

	/**
	 * 2.handle authcode get AcToken
	 * 
	 * @param code2
	 */
	public void handleAuthCode(String code2) {

		// keys
		keys1.add("client_id");
		keys1.add("client_secret");
		keys1.add("redirect_uri");
		// this two may changed
		keys1.add("grant_type");
		// code may change to a refresh_token
		keys1.add("code");
		// values
		values1.add(client_id);
		values1.add(client_secret);
		values1.add(redirect_uri);
		values1.add(grant_type);
		values1.add(code2);

		new Thread() {
			public void run() {
				String re = NetUtils.getHttpEntity(url2, NetUtils.POST, keys1,
						values1);
				Gson gson = new Gson();
				AcToken acToken = gson.fromJson(re, new TypeToken<AcToken>() {
				}.getType());
				sendMessage(acToken);
			};
		}.start();
	}

	/**
	 * attach params to url
	 * 
	 * @param urls
	 * @param values
	 * @param keys
	 * @return
	 */
	private static String getUrlStr(String urls, List<String> values,
			List<String> keys) {
		StringBuffer urlBuffer = new StringBuffer(urls);
		if (keys.size() >= 1) {
			// params start
			urlBuffer.append("?");
			// ensure the right Url encode
			urlBuffer.append(keys.get(0)).append("=").append(values.get(0));
			Log.d("NET",
					"parms" + (1) + ":" + keys.get(0) + "=" + values.get(0));
			for (int i = 1; i < keys.size(); i++) {
				// another key value pair and show the params
				urlBuffer.append("&");
				urlBuffer.append(keys.get(i)).append("=").append(values.get(i));
				Log.d("NET", "parms" + (i + 1) + ":" + keys.get(i) + "="
						+ values.get(i));
			}
		}
		Log.d("NET", "url:" + urlBuffer.toString());
		return urlBuffer.toString();

	}
}
