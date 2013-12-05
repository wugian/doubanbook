package com.study.doubanbook_for_android.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.study.doubanbook_for_android.R;

@EFragment(R.layout.bar_all)
public class BaseFragment extends Fragment {

	// 导航条右边是否可见
	public static final int VISIBLE = View.VISIBLE;
	public static final int INVISIBLE = View.INVISIBLE;
	static final int NOT_FOR_REULST = -110;// 启动activity not for result

	@ViewById
	Button done_btn, back_btn;

	@ViewById
	TextView pageTitle_tv;

	Context context;
	LayoutInflater inflater;

	@AfterViews
	void afterViews() {
		context = getActivity();
		inflater = LayoutInflater.from(context);
		onAfterViews();
	}

	void setNavigationBars(String title, int doneVisible, String rightBtnTitle) {
		pageTitle_tv.setText(title);
		done_btn.setVisibility(doneVisible);
		if (rightBtnTitle != null)
			done_btn.setText(rightBtnTitle);
	}

	void setBackInvisible() {
		back_btn.setVisibility(INVISIBLE);
	}

	protected void onAfterViews() {
		initData();
		initWidgets();
	}

	protected void initWidgets() {
		// TODO Auto-generated method stub
	}

	protected void initData() {
		// TODO Auto-generated method stub
	}

	@UiThread
	public void showToastMsg(String msg) {
		// ShowUtil.toastShow(context, msg);
	}

	@UiThread
	public void hideSoftInput(EditText et) {
		// ShowUtil.hideSoftInput(context, et);
	}

	@UiThread
	protected void replaceFragment(int containerId, Fragment f) {
		getFragmentManager().beginTransaction().replace(containerId, f)
				.commit();
	}

	boolean isEmpty(String s) {
		if (s.equals(""))
			return true;
		return false;
	}

	boolean isZero(int k) {
		if (k == 0) {
			return true;
		}
		return false;
	}

	int getValue(String s) {
		return Integer.parseInt(s);
	}

	@Click
	void back_btn() {
		finish();
	}

	@Click
	void done_btn() {
		doneDetails();
	}

	/**
	 * 如果DONE—BTN可见，则一定要覆盖这个方法，当点击DONE所做的具体事情
	 */
	void doneDetails() {

	}

	protected void finish() {
		getActivity().finish();
	}

	protected void setResult(int resultCode, Intent data) {
		getActivity().setResult(resultCode, data);
	}

	// 曾用名 getStringFromTv
	String getText(TextView tv) {
		return tv.getText().toString().trim();
	}

	/**
	 * 与startActivityWithParamsForResult相比，更加灵活，bundle可以任意添加参数
	 * 
	 * @param className
	 *            跳转到的 fragment 名
	 * @param bundle
	 *            在bundle 里面添加参数所要传递的参数，在跳到的页面用@FragmentArg接收数据,若为空则创建
	 * @param requestCode
	 *            请求码，若为－110则不forResult
	 */
	public void startActivityForResult(String className, Bundle bundle,
			int requestCode) {
		// if (bundle == null)
		// bundle = new Bundle();
		//
		// bundle.putString(GeneralFragmentActivity.ARGS_NAME_WILL_LOAD_FRAGMENT,
		// className);
		// // 经验证可行
		// // bundle.putParcelable(paramsName, (Parcelable) obj);
		// Intent intent = new Intent(context, GeneralFragmentActivity_.class);
		// intent.putExtra("arguments", bundle);
		//
		// if (requestCode == NOT_FOR_REULST) {
		// startActivity(intent);
		// } else {
		// startActivityForResult(intent, requestCode);
		// }
	}
}
