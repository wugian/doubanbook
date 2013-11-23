package com.study.doubanbook_for_android.api;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Rest;

@Rest(rootUrl = "https://api.douban.com/v2", converters = {
		StringHttpMessageConverter.class, SimpleXmlHttpMessageConverter.class })
public interface IApi {

}
