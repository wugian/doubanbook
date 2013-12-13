package com.study.doubanbook_for_android.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;
import android.util.Log;

import com.study.doubanbook_for_android.model.URLMananeger;

public class NetUtils {

	public static final int GET = 0;
	public static final int POST = 1;

	/**
	 * 如果不是返回服务端,或者客服端的错误则在日志里面查看具体错误,用的是NET作为TAG
	 * 
	 * @param urls
	 *            suburl basic is known to us
	 * @param method
	 *            get or post
	 * @param keys
	 *            想要传递给服务器的键值
	 * @param values
	 *            想要传递给服务器的参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getHttpEntity(String urls, int method,
			List<String> keys, List<String> values) {

		HttpResponse httpResponse = null;
		StringBuffer result = new StringBuffer();
		switch (method) {
		case GET:
			HttpGet httpGet = new HttpGet(getUrlStr(urls, values, keys));
			try {
				httpResponse = getNewHttpClient().execute(httpGet);
			} catch (ClientProtocolException e) {
				Log.d("NET", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				Log.d("NET", e.getMessage());
				e.printStackTrace();
			}
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				try {
					result = new StringBuffer(EntityUtils.toString(httpResponse
							.getEntity()));
					// log out the result
					Log.d("NET", result.toString());
				} catch (ParseException e) {
					// log out the exception
					Log.d("NET", e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					Log.d("NET", e.getMessage());
					e.printStackTrace();
				}
			} else {
				// get the wrong msg will return upstairs will charge
				// log out the status code and describe
				try {
					// log out the result,in business will be received by
					// wrongMsg model
					result = new StringBuffer(EntityUtils.toString(httpResponse
							.getEntity()));
					Log.d("NET", result.toString());
				} catch (org.apache.http.ParseException e) {
					Log.d("NET", e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					Log.d("NET", e.getMessage());
					e.printStackTrace();
				}
				Log.d("NET", "status code:  "
						+ httpResponse.getStatusLine().getStatusCode());
				Log.d("NET", "status describe:  "
						+ httpResponse.getStatusLine().getReasonPhrase());
			}
			break;
		case POST:

			break;

		/**
		 * 这个不用使用SSL协议的客服端认证,以前的使用方便现在放弃 updatatime: 2013-12-13
		 */
		case 3 /* old get */:
			try {
				URL url = new URL(getUrlStr(urls, values, keys));
				URLConnection connection = url.openConnection();
				String line;
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return result.toString();
	}

	// ------other basic method
	private static String getBasicUrl(String url) {
		return URLMananeger.ROOT_ULR + url;
	}

	private static String getUrlStr(String urls, List<String> values,
			List<String> keys) {
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(getBasicUrl(urls));
		if (keys.size() >= 1) {
			// params start
			urlBuffer.append("?");
			// ensure the right Url encode
			urlBuffer.append(keys.get(0)).append("=").append(values.get(0));
			for (int i = 1; i < keys.size(); i++) {
				// another key value pair
				urlBuffer.append("&");
				urlBuffer.append(keys.get(i)).append("=").append(values.get(i));
			}
		}
		Log.d("NET", urlBuffer.toString());
		return urlBuffer.toString();
	}

	/**
	 * for https or http connection defined a httpclient
	 * HTTPS:超文本安全传输协议，和HTTP相比，多了一个SSL/TSL的认证过程，端口为443
	 * 1.peer终端发送一个request，https服务端把支持的加密算法等以证书的形式返回一个身份信息（包含ca颁发机构和加密公钥等）。
	 * 2.获取证书之后，验证证书合法性。 3.随机产生一个密钥，并以证书当中的公钥加密。 4.request
	 * https服务端，把用公钥加密过的密钥传送给https服务端。 5.https服务端用自己的密钥解密，获取随机值。
	 * 6.之后双方传送数据都用此密钥加密后通信。
	 * 
	 * @return httpclient
	 */
	public static HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}
}