package com.study.doubanbook_for_android.utils;

import java.io.File; 

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

public class ClearCacheUtils {
	protected Context context;

	public ClearCacheUtils(Context context) {
		super();
		this.context = context;
	}

	/**
	 * clear the cache before time numDays
	 * 调用：clearCacheFolder(PageActivity.getCacheDir(),
	 * System.currentTimeMillis());
	 * 
	 * @param dir
	 * @param numDays
	 * @return
	 */
	public int clearCacheFolder(File dir, long numDays) {
		int deletedFiles = 0;
		if (dir != null && dir.isDirectory()) {
			try {
				for (File child : dir.listFiles()) {
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, numDays);
					}

					if (child.lastModified() < numDays) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedFiles;
	}

	/**
	 * call this method while exit application to delete WebView Cache files and
	 * database files of WebView
	 * 
	 * @param context
	 *            target caller
	 */
	public static void deleteCacheFile(Context context) {
		// File file = CookieManager.getCacheFileBaseDir();
		// if (file != null && file.exists() && file.isDirectory()) {
		// for (File item : file.listFiles()) {
		// item.delete();
		// }
		// file.delete();
		// }
		// context.deleteDatabase("WebView.db");
		// context.deleteDatabase("WebViewCache.db");
	}

	/**
	 * get instances and remove all session cookies
	 * 
	 * @param context
	 */
	public static void removeSessionCookie(Context context) {
		CookieSyncManager.createInstance(context);
		CookieSyncManager.getInstance().startSync();
		CookieManager.getInstance().removeSessionCookie();
	}

	/**
	 * Cache&History
	 * 
	 * @param webView
	 */
	public static void clearCookie(WebView webView) {
		webView.clearCache(true);
		webView.clearHistory();
	}
}
