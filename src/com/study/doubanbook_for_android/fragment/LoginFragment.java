package com.study.doubanbook_for_android.fragment;

import android.widget.EditText;

import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.study.doubanbook_for_android.R;

@EFragment(R.layout.f_login)
public class LoginFragment extends BaseFragment {

	@ViewById
	EditText username_et, password_et;

	@Override
	protected void initWidgets() {
		super.initWidgets();
		
	}
}