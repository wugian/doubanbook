package com.study.doubanbook_for_android;

import java.util.List;

import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.study.doubanbook_for_android.model.BookItem;
import com.study.doubanbook_for_android.model.GeneralResult;
import com.study.doubanbook_for_android.model.TagItem;
import com.study.doubanbook_for_android.utils.ModelUtils;

@EActivity(R.layout.activity_loading_page)
public class LoadingPage extends FragmentActivity {

	void init() {
		// new Thread() {
		// public void run() {
		// // Book all = ModelUtils.getBookList("ss");
		// // System.out.println(all.toString());
		// // GeneralResult<BookItem> all = ModelUtils.getBookList("");
		// // System.out.println(all.toString());
		// };
		// }.start();

		String s = "{"
				+ "'count':1,'start':0,'total':1227731,"
				+ "'books':"
				+ "[{'rating':{'max':10,'numRaters':11845,"
				+ "'average':'8.6','min':0},'subtitle':'','author':['[美] 莫提默·J. 艾德勒','查尔斯·范多伦'],"
				+ "'pubdate':'2004-1',"
				+ "'tags':[{'count':4881,'name':'阅读','title':'阅读'},"
				+ "{'count':3561,'name':'阅读方法','title':'阅读方法'},"
				+ "{'count':3437,'name':'如何阅读一本书','title':'如何阅读一本书'},"
				+ "{'count':2221,'name':'方法论','title':'方法论'},"
				+ "{'count':1952,'name':'读书','title':'读书'},"
				+ "{'count':1843,'name':'学习','title':'学习'},"
				+ "{'count':1825,'name':'方法','title':'方法'},"
				+ "{'count':1676,'name':'读书方法','title':'读书方法'}],"
				+ "'origin_title':'How to Read a Book','image':'http//:img5.douban.commpics1670978.jpg',"
				+ "'binding':'平装',"
				+ "'translator':['郝明义','朱衣'],"
				+ "'catalog':'序言\n第一篇阅读的层次\n第一章阅读的活力与艺术\n主动的阅读・阅读的目标：为获得资讯而读，以及为求得理解而读・阅读就是学习：指导型的学习，以及自我发规型的学习之间的差异・老师的出席与缺席\n第二章阅读的层次\n第三章阅读的第一个层次：基础阅读\n学习阅读的阶段・阅读的阶段与层次・更高层次的阅读与高等教育・阅读与民主教育的理念\n第四章阅读的第二个层次：检视阅读\n检视阅读一：有系统的略读或粗读・检视阅读二：粗浅的阅读・阅读的速度・逗留与倒退・理解的问题・检视阅读的摘要\n第五章如何做一个自我要求的读者\n主动的阅读基础：一个阅读者要提出的四个基本问题・如何让一本书真正属于你自己・三种做笔记的方法・培养阅读的习惯・由许多规则中养成一个习惯……',"
				+ "'pages':'376',"
				+ "'images':{'small':'http:img5.douban.comspics1670978.jpg',"
				+ "'large':'http:img5.douban.comlpics1670978.jpg',"
				+ "'medium':'http:img5.douban.commpics1670978.jpg'},"
				+ "'alt':'http:book.douban.comsubject1013208',"
				+ "'id':'1013208','publisher':'商务印书馆',"
				+ "'isbn10':'7100040949',"
				+ "'isbn13':'9787100040945',"
				+ "'title':'如何阅读一本书',"
				+ "'url':'http:api.douban.comv2book1013208',"
				+ "'alt_title':'How to Read a Book',"
				+ "'author_intro':'莫提默·J. 艾德勒（1902－2001）以学者、教育家、编辑人等多重面貌享有盛名。除了写作《如何阅读一本书》外，以主编《西方世界的经典人并担任1974年第十五版《大英百科全书》的编辑相异而闻名于世。\n查尔斯·范多伦（1926－）先曾任美国哥伦比亚大学教授。后因故离任，和艾德勒一起工作。一方面襄助艾德勒编辑《大英百科全书》，一方面将本书1940年初版内容大幅度增补改写。','summary':'每本书的封面之下都有一套自己的骨架，作为一个分析阅读的读者，责任就是要找出这个骨架。一本书出现在面前时，肌肉包着骨头，衣服包裹着肌肉，可说是盛装而来。读者用不着揭开它的外衣或是撕去它的肌肉来得到在柔软表皮下的那套骨架，但是一定要用一双X光般的透视眼来看这本书，因为那是了解一本书、掌握其骨架的基础。\n《如何阅读一本书》初版于1940年，1972年大幅增订改写为新版。不懂阅读的人，初探阅读的人，读这本书可以少走冤枉路。对阅读有所体会的人，读这本书可以有更深的印证和领悟。',"
				+ "'price':'38.00元'}]}";
		Gson gson = new Gson();
		// GeneralResult<BookItem> all = gson.fromJson(s,
		// new TypeToken<GeneralResult<BookItem>>() {
		// }.getType());
		// System.out.println(all.toString());
		List<TagItem> tg = gson.fromJson(
				"[{'count':4881,'name':'阅读','title':'阅读'},"
						+ "{'count':3561,'name':'阅读方法','title':'阅读方法'},"
						+ "{'count':3437,'name':'如何阅读一本书','title':'如何阅读一本书'},"
						+ "{'count':2221,'name':'方法论','title':'方法论'},"
						+ "{'count':1952,'name':'读书','title':'读书'},"
						+ "{'count':1843,'name':'学习','title':'学习'},"
						+ "{'count':1825,'name':'方法','title':'方法'},"
						+ "{'count':1676,'name':'读书方法','title':'读书方法'}]",
				new TypeToken<List<TagItem>>() {
				}.getType());

		System.out.println(tg.size());

	}

	class Result {
		String rating;

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

	}
}
