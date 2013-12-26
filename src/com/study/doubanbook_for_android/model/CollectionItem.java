package com.study.doubanbook_for_android.model;

import java.io.Serializable;

public class CollectionItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4732649630352781579L;
	
	private String status;//":"wish",
	private String comment;//":"书中的主人公是很多人的真是写照揭露了丑恶的社会现象",
	private String updated;//":"2013-12-25 22:09:04","user_id":"72719211",
	private BookItem book;//":{"rating":{"max":10,"numRaters":23215,"average":"8.6","min":0},"subtitle":"","author":["鲁迅","丰子恺"],"pubdate":"2003-7-1","tags":[{"count":5503,"name":"鲁迅","title":"鲁迅"},{"count":3170,"name":"阿Q正传","title":"阿Q正传"},{"count":1992,"name":"中国文学","title":"中国文学"},{"count":1836,"name":"小说","title":"小说"},{"count":1158,"name":"经典","title":"经典"},{"count":739,"name":"现代文学","title":"现代文学"},{"count":626,"name":"中国","title":"中国"},{"count":513,"name":"文学","title":"文学"}],"origin_title":"阿q正传","image":"http:\/\/img3.douban.com\/mpic\/s1074811.jpg","binding":"平装","translator":[],"catalog":"导读・何满子\n俄文译本《阿Q正传》序\n第一章 序\n第二章 优胜记略\n第三章 续优胜记略\n第四章 恋爱的悲剧\n第五章 生计问题\n第六章 从中兴到末路\n第七章 革命\n第八章 不准革命\n第九章 大团圆\n附录 《阿Q正传》的成因","pages":"189","images":{"small":"http:\/\/img3.douban.com\/spic\/s1074811.jpg","large":"http:\/\/img3.douban.com\/lpic\/s1074811.jpg","medium":"http:\/\/img3.douban.com\/mpic\/s1074811.jpg"},"alt":"http:\/\/book.douban.com\/subject\/1088065\/","id":"1088065","publisher":"上海书店出版社","isbn10":"7806222855","isbn13":"9787806222850","title":"阿Q正传","url":"http:\/\/api.douban.com\/v2\/book\/1088065","alt_title":"阿q正传","author_intro":"《阿Q正传》讲述值此世纪末，衷心祈愿这里所议论的人物在下一世纪的中国现实生活中淡出；当然，作为光辉的典型人物，他将和哈姆雷特、唐·吉诃德等不朽的名字一样万古不朽。阿Q，是一个卑微渺小的人物，但却是一个巨大的名字。我不说：“伟大”而说“巨大”，是因为这个小人物的确称不上伟大，但这个名字的历史的和美学的涵容量却真是巨大得无比，我想不出世界任何一个文学人物能有阿Q那样巨大的概括性，把几亿人都涵盖进去。几乎每个中国人，你，我，他，都有阿Q的灵魂的因子。阿Q是一个“做奴隶而不可得的时代”的奴隶，比“做稳了奴隶的时代”的奴隶更可悲。做不稳，就要挣扎。他的挣扎当然不可能有过五关、斩六将或爬雪山、过草地那样的英雄事业，但作为一个小人物，他的一生也够悲壮的了。使阿Q的一生焕发悲壮的光采的是，他不仅呈现了清末民初的一个普通百姓的灵魂，几千年来成群而生、成群而死的默默的生...","summary":"《阿Q正传》讲述值此世纪末，衷心祈愿这里所议论的人物在下一世纪的中国现实生活中淡出；当然，作为光辉的典型人物，他将和哈姆雷特、唐·吉诃德等不朽的名字一样万古不朽。阿Q，是一个卑微渺小的人物，但却是一个巨大的名字。我不说：“伟大”而说“巨大”，是因为这个小人物的确称不上伟大，但这个名字的历史的和美学的涵容量却真是巨大得无比，我想不出世界任何一个文学人物能有阿Q那样巨大的概括性，把几亿人都涵盖进去。几乎每个中国人，你，我，他，都有阿Q的灵魂的因子。阿Q是一个“做奴隶而不可得的时代”的奴隶，比“做稳了奴隶的时代”的奴隶更可悲。做不稳，就要挣扎。他的挣扎当然不可能有过五关、斩六将或爬雪山、过草地那样的英雄事业，但作为一个小人物，他的一生也够悲壮的了。使阿Q的一生焕发悲壮的光采的是，他不仅呈现了清末民初的一个普通百姓的灵魂，几千年来成群而生、成群而死的默默的生长和枯死的灵魂；更是藉着这个灵魂的勾勒，多方面地全国民性的痼疾爆了光。阿Q虽然枪毙了，但他的阴魂不散，时时附在中国人的身上。因为痼疾的病灶埋藏得既深且久，不时复发，至少在本世纪还是常见病和多发病。但愿即将降临的新世纪，这个人物在中国淡出，只留下无与伦比的形象在艺苑中永生。这肯定也是创造这一典型人物的鲁迅的夙愿。",
	private String price;//":"14.50"},
	private int book_id;//":"1088065",
	private int id;//":757556926},
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public BookItem getBook() {
		return book;
	}
	public void setBook(BookItem book) {
		this.book = book;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
