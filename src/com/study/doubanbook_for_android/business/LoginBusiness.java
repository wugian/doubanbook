package com.study.doubanbook_for_android.business;

import java.util.ArrayList;
import java.util.List;

import com.study.doubanbook_for_android.api.NetUtils;

public class LoginBusiness {
	private String client_id = "09561172f001e8251c023458d005b86f";// 必选参数，应用的唯一标识，对应于APIKey
	private String redirect_uri = "http://sns.whalecloud.com/douban/callback";// 用户授权完成后的回调地址，应用需要通过此回调地址获得用户的授权结果。
	private String response_type = "code";// 必选参数，此值可以为 code 或者 token 。在本流程中，此值为
											// code
	private String scope;// 可选参数，申请权限的范围，如果不填，则使用缺省的scope。如果申请多个scope，使用逗号分隔。
	private String state;// 可选参数，用来维护请求和回调状态的附加字符串，在授权完成回调时会附加此参数，应用可以根据此字符串来判断上下文关系。

	private String url1 = "https://www.douban.com/service/auth2/auth";

	public String getAutorizationCode() {
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		keys.add("client_id");
		keys.add("redirect_uri");
		keys.add("response_type");
		values.add(client_id);
		values.add(redirect_uri);
		values.add(response_type);
		String s = NetUtils.getHttpEntity(url1, NetUtils.GET, keys, values,
				null);
		return s;
	}
}
