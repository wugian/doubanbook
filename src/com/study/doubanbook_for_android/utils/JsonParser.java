package com.study.doubanbook_for_android.utils;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonParser {
	/**
	 * ����������������б�
	 * 
	 * @param json
	 * @param token
	 *            �� new TypeToken<ArrayList<TYPE>>(){};
	 * @return ArrayList<TYPE>
	 */
	public static <T> ArrayList<T> getFromJson(String json, TypeToken<?> token) {
		Gson gson = new Gson();
		// ArrayList<T> all = gson.fromJson(json, token.getType());
		return gson.fromJson(json, token.getType());
	}

	public static <T> T getFromJsonstr(String json, TypeToken<?> token) {
		Gson gson = new Gson();
		// ArrayList<T> all = gson.fromJson(json, token.getType());
		return gson.fromJson(json, token.getType());
	}

	/**
	 * ��ArrayList<TYPE>ת����JSON��ʽ���ַ�
	 * 
	 * @param all
	 *            = ArrayList<TYPE>
	 * @return
	 */
	public <T> String parseToJsons(ArrayList<T> all) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String result = gson.toJson(all);
		return result;

	}

}
