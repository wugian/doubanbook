package com.study.doubanbook_for_android.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import com.study.doubanbook_for_android.model.URLMananeger;

import android.util.Log;

public class NetUtils {

	public static final int GET = 0;
	public static final int POST = 1;

	static String downloadStr(String urlstr) {
		StringBuffer result = new StringBuffer();
		Log.i("NET", "request url:" + urlstr);
		try {
			URL url = new URL(urlstr);
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
		return result.toString();
	}

	public static String getContent(String url) throws Exception {
		StringBuilder sb = new StringBuilder();

		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		// 设置网络超时参数
		// HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
		// HttpConnectionParams.setSoTimeout(httpParams, 5000);
		HttpResponse response = client.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"), 8192);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();
		}
		return sb.toString();
	}

	public static String getHttpEntity(String urls, int method,
			List<String> keys, List<String> values)
			throws UnsupportedEncodingException {

		HttpResponse httpResponse = null;
		String results = null;
		StringBuffer result = null;
		switch (method) {
		case GET:
			StringBuffer urlBuffer = new StringBuffer();
			urlBuffer.append(getBasicUrl(urls));
			if (keys.size() >= 1) {
				// params start
				urlBuffer.append("?");
				// ensure the right url encode
				urlBuffer.append(keys.get(0)).append("=").append(values.get(0));
				for (int i = 1; i < keys.size(); i++) {
					// another key value pair
					urlBuffer.append("&");
					urlBuffer.append(keys.get(i)).append("=")
							.append(values.get(i));
				}
			}
			Log.d("NET", urlBuffer.toString());
			// HttpGet httpGet = new HttpGet(urlBuffer.toString());
			// String s = URLEncoder.encode("鲁迅", "UTF-8");
			// HttpGet httpGet = new HttpGet(
			// "https://api.douban.com/v2/book/search?q=" + s
			// + "&start=1&count=10");
			// try {
			// httpResponse = new DefaultHttpClient().execute(httpGet);
			// } catch (ClientProtocolException e) {
			// Log.d("NET", e.getMessage());
			// e.printStackTrace();
			// } catch (IOException e) {
			// Log.d("NET", e.getMessage());
			// e.printStackTrace();
			// }
			// if (httpResponse.getStatusLine().getStatusCode() ==
			// HttpStatus.SC_OK) {
			// try {
			// result = EntityUtils.toString(httpResponse.getEntity());
			// Log.d("NET", result);
			// } catch (ParseException e) {
			// Log.d("NET", e.getMessage());
			// e.printStackTrace();
			// } catch (IOException e) {
			// Log.d("NET", e.getMessage());
			// e.printStackTrace();
			// }
			// return result;
			// } else {
			// Log.d("NET", "status code:  "
			// + httpResponse.getStatusLine().getStatusCode());
			// Log.d("NET", "status describe:  "
			// + httpResponse.getStatusLine().getReasonPhrase());
			// }
			result = new StringBuffer();
			Log.i("NET", "request url:" + urlBuffer.toString());
			try {
				URL url = new URL(urlBuffer.toString());
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
		case POST:

			break;
		default:
			break;
		}
		return result.toString();
	}

	private static String getBasicUrl(String url) {
		return URLMananeger.ROOT_ULR + url;
	}
}
