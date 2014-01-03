package com.study.doubanbook_for_android.xmlpaser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
	private static final int ENTRY_AUTHOR_NAME = 111;
	private static final int ENTRY_AUTHOR_URI = 112;
	CommentReslult feed;
	Entry entryItem;

	final int FEED_TITLE = 11;
	final int FEED_TOTAL = 12;
	final int FEED_COUNT = 13;
	final int FEED_START = 14;
	
	final int ENTRY_ID = 1;
	final int ENTRY_TITLE = 2;
	final int ENTRY_PUBLISHED = 3;
	final int ENTRY_UPDATED = 4;
	final int ENTRY_SUMMARY = 5;
	final int ENTRY_AUTHOR = 6;
	final int AUTHOR_NAME = 7;
	final int AUTHOR_URI = 8;

	int currentstate = 0;

	public SaxHandler() {
	}

	public CommentReslult getFeed() {
		return feed;
	}

	@Override
	public void startDocument() throws SAXException {
		feed = new CommentReslult();
		entryItem = new Entry();
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO get local name then compare with the attribute and initial the
		// status
		if (localName.equals("feed")) {
			currentstate = 0;
			return;
		}
		if (localName.equals("entry")) {
			entryItem = new Entry();
			return;
		}
		if (localName.equals("id")) {
			currentstate = ENTRY_ID;
			return;
		}
		if (localName.equals("published")) {
			currentstate = ENTRY_PUBLISHED;
			return;
		}
		if (localName.equals("updated")) {
			currentstate = ENTRY_UPDATED;
			return;
		}
		if (localName.equals("category")) {
			currentstate = ENTRY_UPDATED;
			return;
		}
		if (localName.equals("summary")) {
			currentstate = ENTRY_SUMMARY;
		
			
			return;
		}
		if (localName.equals("name")) {
			currentstate = ENTRY_AUTHOR_NAME;
			return;
		}
		if (localName.equals("uri")) {
			currentstate = ENTRY_AUTHOR_URI;
			return;
		}
		currentstate = 0;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO add list item
		if (localName.equals("item")) {
			feed.addItem(entryItem);
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String theString = new String(ch, start, length);
		switch (currentstate) {
		// TODO set the attribute
		case ENTRY_ID:
			entryItem.setTitle(theString);
			currentstate = 0;
			break;
		default:
			return;
		}
	}
}
