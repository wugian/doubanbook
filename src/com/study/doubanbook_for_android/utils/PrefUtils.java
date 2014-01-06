/*
 * KeepToken.java
 * classes : org.kevin.douban.auth.KeepToken 
 * Created at : 2013-5-14 上午11:16:20
 */
package com.study.doubanbook_for_android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * save token to the preference
 * 
 */
public class PrefUtils {
	private static final String PREFERENCES_NAME = "douban_android_setting";

	public static void saveAutoLogin(Context context, boolean b) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME,
				Context.MODE_APPEND);
		Editor editor = pref.edit();
		editor.putBoolean("auto_login", b);
		editor.commit();
	}

	public static boolean getAutoLogin(Context context) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME,
				Context.MODE_APPEND);
		return pref.getBoolean("auto_login", false);
	}

	/**
	 * 清空sharepreference
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME,
				Context.MODE_APPEND);
		Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}

}
